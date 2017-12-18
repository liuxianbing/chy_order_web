package com.chy.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chy.order.core.AbstractService;
import com.chy.order.mapper.UserMapper;
import com.chy.order.model.User;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月6日 下午5:51:58 
* 类说明 
*/
@Service
@Transactional
public class UserService  extends AbstractService< UserMapper,  User>{

	
	public User login(String user,String pass){
		EntityWrapper<User> wrapper=new EntityWrapper<>();
		wrapper.eq("user", user);
		wrapper.eq("pass", pass);
		return selectOne(wrapper);
	}
}
