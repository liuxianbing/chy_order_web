package com.chy.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chy.order.core.AbstractService;
import com.chy.order.mapper.AddressMapper;
import com.chy.order.model.Address;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月7日 下午3:50:24 
* 类说明 
*/
@Service
@Transactional
public class AddressService  extends AbstractService< AddressMapper,  Address>{

	
	public List<Address> selectByUid(long uid){
		EntityWrapper<Address> wrapper=new EntityWrapper<>();
		wrapper.eq("uid", uid);
		return selectList(wrapper);
	}
	
	public void setDefaultAddr(Long uid,Long id){
		EntityWrapper<Address> wrapper=new EntityWrapper<>();
		wrapper.eq("uid", uid);
		updateBatchById(selectList(wrapper).stream().map(m->{
			Address addr=new Address();
			addr.setId(m.getId());
			addr.setSt(0);
			return addr;
		}).collect(Collectors.toList()));
		Address addr=new Address();
		addr.setId(id);
		addr.setSt(1);
		updateById(addr);
	}
}
