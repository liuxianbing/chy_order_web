package com.chy.order.web;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chy.order.model.User;
import com.chy.order.util.Constants;
import com.chy.order.util.WebUtil;
import com.chy.order.vo.Result;
import com.chy.order.vo.ResultCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月7日 上午10:15:38 
* 类说明 
*/
@Api(value = "登录管理", description = "登录处理模块")
@Controller
public class LoginController extends AbstractController {

	
	@ApiOperation(value = "登录请求")
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Result<String> login(@RequestBody User user){
		User tmp=userService.login(user.getUser(), DigestUtils.md5Hex(user.getPass()));
		if(tmp==null){
			return new Result<>(ResultCode.ERROR,"用户名或密码错误");
		}else{
			WebUtil.setCurrentUser(request, tmp);
			return new Result<>(ResultCode.SUCCEED,"");
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	 @ApiOperation(value = "退出登录")
	@ResponseBody
	public Map<String,String> logout() throws Exception{
		request.getSession().removeAttribute(Constants.CURRENT_USER);
		request.getSession().invalidate();
		 return SUCCESS;
	}
}
