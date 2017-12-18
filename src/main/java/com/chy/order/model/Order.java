package com.chy.order.model;
/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月16日 下午2:37:31 
* 类说明 
*/

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;
@TableName("orders")
public class Order implements Serializable{
	
	private static final long serialVersionUID = -7269398948910605127L;
	private Long id;
	private String contract_no,data_order;
	private Integer product_quantity;
	private String tax;
	private Double full_payment_rate,down_payment;
	private Integer order_seq,payment_seq,determine,up_pay_start,mode;
	private String payment_url,payment_final_url,express_type;
	private Integer pay_type;
	private Long uid,delivery_id;
	private String type;
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExpress_type() {
		return express_type;
	}
	public void setExpress_type(String express_type) {
		this.express_type = express_type;
	}
	public Long getDelivery_id() {
		return delivery_id;
	}
	public void setDelivery_id(Long delivery_id) {
		this.delivery_id = delivery_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContract_no() {
		return contract_no;
	}
	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}
	public String getData_order() {
		return data_order;
	}
	public void setData_order(String data_order) {
		this.data_order = data_order;
	}
	public Integer getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public Double getFull_payment_rate() {
		return full_payment_rate;
	}
	public void setFull_payment_rate(Double full_payment_rate) {
		this.full_payment_rate = full_payment_rate;
	}
	public Double getDown_payment() {
		return down_payment;
	}
	public void setDown_payment(Double down_payment) {
		this.down_payment = down_payment;
	}
	public Integer getOrder_seq() {
		return order_seq;
	}
	public void setOrder_seq(Integer order_seq) {
		this.order_seq = order_seq;
	}
	public Integer getPayment_seq() {
		return payment_seq;
	}
	public void setPayment_seq(Integer payment_seq) {
		this.payment_seq = payment_seq;
	}
	public Integer getDetermine() {
		return determine;
	}
	public void setDetermine(Integer determine) {
		this.determine = determine;
	}
	public Integer getUp_pay_start() {
		return up_pay_start;
	}
	public void setUp_pay_start(Integer up_pay_start) {
		this.up_pay_start = up_pay_start;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	public String getPayment_url() {
		return payment_url;
	}
	public void setPayment_url(String payment_url) {
		this.payment_url = payment_url;
	}
	public String getPayment_final_url() {
		return payment_final_url;
	}
	public void setPayment_final_url(String payment_final_url) {
		this.payment_final_url = payment_final_url;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	
	

}
