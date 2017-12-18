package com.chy.order.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chy.order.core.AbstractService;
import com.chy.order.mapper.ProductMapper;
import com.chy.order.model.Product;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月11日 下午12:52:40 
* 类说明 
*/
@Service
@Transactional
public class ProductService extends AbstractService< ProductMapper,  Product> {

	public List<Product> selectList(Map<String,Object> params){
		EntityWrapper<Product> wrapper=new EntityWrapper<>();
		if(params.size()>0 && params.get("data1")!=null && params.get("data2")!=null ){
			wrapper.ge("create_time", params.get("data1"));
			wrapper.le("create_time", params.get("data2"));
		}
		return selectList(wrapper);
	}
	
	public void delProduct(String ids){
		String[] id=ids.split(",");
		for(String s:id){
			super.deleteById(Long.parseLong(s));
		}
	}
	
	public List<Product> selectList(String ids){
		String[] id=ids.split(",");
		List<Long> list=Arrays.asList(id).stream().map(m->Long.parseLong(m)).collect(Collectors.toList());
		EntityWrapper<Product> wrapper=new EntityWrapper<>();
		wrapper.in("id", list);
		return selectList(wrapper);
	}
	
	
}
