package com.cn.chy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chy.order.model.User;
import com.chy.order.service.UserService;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年9月27日 下午12:48:52 
* 类说明 
*/
@ContextConfiguration(locations = {"classpath:spring/datasource-config-druid.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

	@Autowired
	UserService userService;
	
	 public static String getAlpha(String chines) {
	        String pinyinName = "";
	        char[] nameChar = chines.toCharArray();
	        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
	        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
	        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	        for (int i = 0; i < nameChar.length; i++) {
	            if (nameChar[i] > 128) {
	                try {
	                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(
	                            nameChar[i], defaultFormat)[0].charAt(0);
	                } catch (BadHanyuPinyinOutputFormatCombination e) {
	                    e.printStackTrace();
	                }
	            } else {
	                pinyinName += nameChar[i];
	            }
	        }
	        return pinyinName;
	    }
	@Test
	public void testAdd(){
//		User user=new User();
//		user.setPass("AAAAAAAAAAA");
//		user.setUser("lxb");
//		userService.insert(user);
            System.out.println( getAlpha("陕西"));
	}
}
