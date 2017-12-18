package com.chy.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chy.order.core.AbstractService;
import com.chy.order.mapper.BomMapper;
import com.chy.order.model.Bom;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月11日 下午3:11:10 
* 类说明 
*/
@Service
@Transactional
public class BomService  extends AbstractService< BomMapper,  Bom> {

	
	public List<Bom> selectByProductId(Long id){
		EntityWrapper<Bom> wrapper=new EntityWrapper<>();
		wrapper.eq("product_id", id);
		return selectList(wrapper);
	}
	
	public void delByProductId(Long id){
		EntityWrapper<Bom> wrapper=new EntityWrapper<>();
		wrapper.eq("product_id", id);
		delete(wrapper);
	}
}
