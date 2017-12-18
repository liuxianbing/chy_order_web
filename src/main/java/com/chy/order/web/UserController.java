package com.chy.order.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.chy.order.model.Address;
import com.chy.order.model.User;
import com.chy.order.util.DateUtil;
import com.chy.order.util.DateUtil.DATE_PATTERN;
import com.chy.order.vo.Result;
import com.chy.order.vo.ResultCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月7日 下午2:27:02 
* 类说明 
*/
@Api(value = "用户管理", description = "用户处理模块")
@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController{

	
	@RequestMapping(value = "info", method = RequestMethod.GET)
	 @ApiOperation(value = "获取用户基本信息")
	@ResponseBody
	public Result<User> queryHeadImg() throws Exception{
		User u=userService.selectById(getCurrUser().getId());
		u.setPerson_address(addressService.selectByUid(getCurrUser().getId()));
		Result<User> res=new Result<>(ResultCode.SUCCEED,u);
		 return res;
	}
	
	@RequestMapping(value = "editAddr", method = RequestMethod.GET)
	 @ApiOperation(value = "获取单条地址信息")
	@ResponseBody
	public Result<Address> editAddr(@RequestParam Long id) throws Exception{
		 return new Result<>(ResultCode.SUCCEED,addressService.selectById(id));
	}
	
	
	@RequestMapping(value = "defaultAddr", method = RequestMethod.GET)
	 @ApiOperation(value = "设置默认地址")
	@ResponseBody
	public Result<Boolean> defaultAddr(@RequestParam Long id) throws Exception{
		addressService.setDefaultAddr(getCurrUser().getId(), id);
		 return new Result<>(ResultCode.SUCCEED,true);
	}
	
	
	
	
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = { "application/json" })
	 @ApiOperation(value = "更新用户基本信息")
	@ResponseBody
	public Result<Boolean> update(@RequestBody User user) throws Exception{
		user.setId(getCurrUser().getId());
		userService.updateById(user);
		return new Result<>(ResultCode.SUCCEED,true);
	}	
	
	@RequestMapping(value = "address", method = RequestMethod.POST, produces = { "application/json" })
	 @ApiOperation(value = "添加或者更新地址信息")
	@ResponseBody
	public Result<Address> address(@RequestBody Address addr) throws Exception{
		addr.setUid(getCurrUser().getId());
		addressService.insertOrUpdate(addr);
		return new Result<>(ResultCode.SUCCEED,addr);
	}
	
	
	 @RequestMapping(value = "/uploadHeadImg")
	 @ApiOperation(value = "上传头像")
	    public @ResponseBody Map<String, String> businessUpload(@RequestParam("file") CommonsMultipartFile upload, HttpServletResponse response,
	        HttpServletRequest request, Model model) throws IOException {
		// String path=PropertiesFileReader.getPropertiesVal("zebra.properties", "auth.pic.url");
		// String subPath=path.substring(path.lastIndexOf("/")+1);
		// checkDir(path);
		  // String url="auth_business";//PropertiesUtil.getString("auth.business.url")
		   String fileType = upload.getOriginalFilename().substring(upload.getOriginalFilename().lastIndexOf(".") + 1);
		   String realpath = request.getSession().getServletContext().getRealPath("/");
		   String storeName = upload.getOriginalFilename().replace(fileType, "") + "_" + DateUtil.getDateTime(DATE_PATTERN.YYYYMMDDHHMMSS) + "." + fileType;
		   FileUtils.copyInputStreamToFile(upload.getInputStream(), new File(realpath+File.separator+"images"+File.separator+storeName));
		   Map<String,String> res=new HashMap<String, String>();
		  // res.put("filePath", realpath+File.separator+storeName);
		//   res.put("filePath", "images"+File.separator+subPath+File.separator+storeName);
		   User user=new User();
		   user.setId(getCurrUser().getId());
		   user.setHead_img("/images/"+storeName);
		   userService.updateById(user);
		   return res;
	 }
}
