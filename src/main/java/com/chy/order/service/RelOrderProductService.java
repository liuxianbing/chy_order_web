package com.chy.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chy.order.core.AbstractService;
import com.chy.order.mapper.RelOrderProductMapper;
import com.chy.order.model.RelOrderProduct;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月17日 下午3:27:20 
* 类说明 
*/
@Service
@Transactional
public class RelOrderProductService extends AbstractService<RelOrderProductMapper,  RelOrderProduct> {

}
