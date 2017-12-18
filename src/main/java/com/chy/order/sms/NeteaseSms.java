package com.chy.order.sms;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chy.order.util.JackSonUtil;

/** 
* @author liuxianbing: 
* @version ����ʱ�䣺2017��12��5�� ����5:36:25 
* ��˵�� 
*/
public class NeteaseSms {

	protected static Logger logger = LogManager.getLogger();
	
	public static final String SEND_URL="https://api.netease.im/sms/sendcode.action";
	
	public static final String VERIFY_URL="https://api.netease.im/sms/verifycode.action";
	
	public static final String appkey="97fcd017f1393309f29ae4555577cc81";
	
	public static String sendMobile(String mobile) throws Exception{
		String content = "mobile=" + URLEncoder.encode(mobile, "utf-8"); 
		String res= sendSms(SEND_URL,content);
		Map<String,Object> maps=JackSonUtil.readValueAsObjFromStr(res, Map.class);
		if(maps.get("code").toString().equals("200")){
			return maps.get("obj").toString();
		}
		logger.error(res);
		throw new RuntimeException("发送验证码失败");
	}
	
	public static String verifyCode(String mobile,String code) throws Exception{
		String content = "mobile=" + mobile+"&code="+code; 
		String res= sendSms(VERIFY_URL,content);
		Map<String,Object> maps=JackSonUtil.readValueAsObjFromStr(res, Map.class);
		if(maps.get("code").toString().equals("200")){
			return maps.get("code").toString();
		}
		logger.error(res);
		throw new RuntimeException("校验验证码失败");
	}
	
	private static String sendSms(String url,String content) throws Exception {
		URL reqUrl = new URL(url);
		HttpURLConnection reqConnection = (HttpURLConnection) reqUrl.openConnection();
		reqConnection.setRequestMethod("POST");
		reqConnection.setUseCaches(false);    
		reqConnection.setRequestProperty("AppKey", appkey);
		Long curTime=new Date().getTime()/1000;
		String nonce=RandomUtils.nextDouble()+"";
		//String nonce="4tgggergigwow323t23t";
		String checkSum =CheckSumBuilder.getCheckSum("1cb67bb2188d",nonce,curTime+"");
		reqConnection.setRequestProperty("Nonce", nonce);
		reqConnection.setRequestProperty("CurTime", curTime+"");
		reqConnection.setRequestProperty("CheckSum", checkSum);
		reqConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		reqConnection.setDoInput(true);
		reqConnection.setDoOutput(true);
		int count = 0;
		boolean flag = false;
		while (!flag && count < 10) {
			Thread.sleep(50);
			flag = reConnect(reqConnection);
			count++;
		}
		
		DataOutputStream out = new DataOutputStream(reqConnection.getOutputStream());   
        out.writeBytes(content);  
        out.flush();  
        out.close();  
        
		 InputStream input  = reqConnection.getInputStream();
	        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
			int rc = 0;  
			while ((rc = input.read(buff, 0, 100)) > 0) {  
				swapStream.write(buff, 0, rc);  
			}  
			return  new String(swapStream.toByteArray(),"utf-8");
	}
	
	private static boolean reConnect(HttpURLConnection reqConnection) {
		try {
			reqConnection.connect();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		//System.out.println("FFFFFFFFFF"+verifyCode("18612985596","1132"));
		System.out.println(sendMobile("18612985596"));
	}
}
