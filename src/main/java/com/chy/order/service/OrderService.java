package com.chy.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chy.order.core.AbstractService;
import com.chy.order.mapper.OrderMapper;
import com.chy.order.model.Order;
import com.chy.order.util.DateUtil;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月16日 下午3:41:18 
* 类说明 
*/
@Service
@Transactional
public class OrderService  extends AbstractService< OrderMapper,  Order>{

	public int selectYearOrderCnt(){
		EntityWrapper<Order> wrapper=new EntityWrapper<>();
		wrapper.like("data_order", DateUtil.getDate().substring(0,4));
		return selectList(wrapper).size();
	}
}
