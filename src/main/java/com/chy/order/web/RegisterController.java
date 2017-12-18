package com.chy.order.web;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chy.order.model.User;
import com.chy.order.service.UserService;
import com.chy.order.sms.NeteaseSms;
import com.chy.order.vo.Result;
import com.chy.order.vo.ResultCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月6日 下午6:11:32 
* 类说明 
*/
@Api(value = "注册管理", description = "注册处理模块")
@Controller
public class RegisterController extends AbstractController {

	
	@ApiOperation(value = "注册请求")
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Result<Boolean> register(@RequestBody User user) throws Exception{
		user.setPass(DigestUtils.md5Hex(user.getPass()));
		NeteaseSms.verifyCode(user.getBasic_phone(),user.getSecurity());
		userService.insert(user);
		return new Result<>(ResultCode.SUCCEED,true);
	}
	
	
	@ApiOperation(value = "发送验证码")
	@RequestMapping(value = "sendSms", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Result<String> sendSms(@RequestBody Map<String,Object> params) throws Exception{
		String num=NeteaseSms.sendMobile(params.get("phone").toString());
		return new Result<>(ResultCode.SUCCEED,num);
	}
	
	@ApiOperation(value = "发送验证码")
	@PostMapping(value = "/checkSms")
	public @ResponseBody Result<String> checkSms(@RequestBody Map<String,String> params) throws Exception{
		String num=NeteaseSms.verifyCode(params.get("phone"),params.get("code"));
		return new Result<>(ResultCode.SUCCEED,num);
	}
	
	
}
