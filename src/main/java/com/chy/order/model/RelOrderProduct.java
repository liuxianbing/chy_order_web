package com.chy.order.model;

import java.io.Serializable;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月17日 下午3:22:49 
* 类说明 
*/
public class RelOrderProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3945791528861124331L;
	private Long id;
	private Long order_id;
	private Long product_id;
	private Integer product_quantity;
	private Double sale;
	private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public Integer getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}
	public Double getSale() {
		return sale;
	}
	public void setSale(Double sale) {
		this.sale = sale;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
