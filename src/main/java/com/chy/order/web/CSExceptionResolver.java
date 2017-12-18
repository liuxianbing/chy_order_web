package com.chy.order.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.chy.order.util.JackSonUtil;
import com.chy.order.vo.ResultCode;


public class CSExceptionResolver extends SimpleMappingExceptionResolver {

  @Override
  protected ModelAndView doResolveException(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex) {
	  ex.printStackTrace();
    String viewName = determineViewName(ex, request);
    if (viewName != null) {// JSP格式返回
    	  Map<String,String> res=new HashMap<>();
        try {
        	if(null!=ex.getMessage() && ex.getMessage().contains("Duplicate entry")){
        		int be=ex.getMessage().lastIndexOf("for key");
        		int en=ex.getMessage().lastIndexOf("_UNIQUE");
        		res.put("msg", "字段"+ex.getMessage().substring(be+9,en)+"取值已经存在");
        	}else if(null!=ex.getMessage() && ex.getMessage().length()>=50){
        		res.put("msg", "系统走神了,请稍候再试.");
        	}else{
        		res.put("msg", ex.getMessage());
        	}
        	res.put("code", ResultCode.ERROR.getCode());
          PrintWriter writer = response.getWriter();
          writer.write(JackSonUtil.getObjectMapper().writeValueAsString(res));
          writer.flush();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return null;
    } else {
      return null;
    }
  }

}
