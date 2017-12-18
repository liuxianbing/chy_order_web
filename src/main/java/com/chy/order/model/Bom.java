package com.chy.order.model;

import java.io.Serializable;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月11日 下午3:04:52 
* 类说明 
*/
public class Bom  implements Serializable{

	private static final long serialVersionUID = -4714756127168417719L;
	private Long id;
	private String number;
	private String name;
	private String model_number;
	private Integer encapsulation;
	private String accuracy;
	private Integer brands;
	private String bit_number;
	private Integer quantity;
	private Double price;
	private String remark;
	private Long product_id;
	private String create_time;
	private String seq_code;
	
	
	public String getSeq_code() {
		return seq_code;
	}
	public void setSeq_code(String seq_code) {
		this.seq_code = seq_code;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel_number() {
		return model_number;
	}
	public void setModel_number(String model_number) {
		this.model_number = model_number;
	}
	public Integer getEncapsulation() {
		return encapsulation;
	}
	public void setEncapsulation(Integer encapsulation) {
		this.encapsulation = encapsulation;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public Integer getBrands() {
		return brands;
	}
	public void setBrands(Integer brands) {
		this.brands = brands;
	}
	public String getBit_number() {
		return bit_number;
	}
	public void setBit_number(String bit_number) {
		this.bit_number = bit_number;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
}
