package com.chy.order.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月7日 下午3:47:01 
* 类说明 
*/
@TableName("address")
public class Address  implements Serializable{

	private static final long serialVersionUID = 3585058704166358267L;
	private String name;
	private String phone;
	private Long id;
	private Long uid;
	private Integer st;
	private String encapsulation;
	private String district;
	private String city;
	private String province;
	
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Integer getSt() {
		return st;
	}
	public void setSt(Integer st) {
		this.st = st;
	}
	public String getEncapsulation() {
		return encapsulation;
	}
	public void setEncapsulation(String encapsulation) {
		this.encapsulation = encapsulation;
	}
	
	
}
