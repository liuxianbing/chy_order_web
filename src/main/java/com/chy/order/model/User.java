package com.chy.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/** 
* @author liuxianbing: 
* @version ����ʱ�䣺2017��12��6�� ����2:19:31 
* ��˵�� 
*/
@TableName("user")
public class User implements Serializable {

	private static final long serialVersionUID = -7499963284995108501L;
	private Long id;
	private String head_img;
	private String basic_numbering;
	private String basic_ompany;
	private String basic_phone;
	private String basic_qq;
	private String basic_mail;
	private String basic_fax;
	private String vat_company;
	private String vat_ein;
	private String vat_address;
	private String vat_opening_bank;
	private String vat_phone;
	private String vat_account;
	private String billing_person;
	private String billing_phone;
	private String billing_address;
	private String user;
	private String create_time;
	private String pass;
	private String province;
	
	@TableField(exist = false)
	private List<Address> person_address=new ArrayList<>();
	
	@TableField(exist = false)
	private String security;
	
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public List<Address> getPerson_address() {
		return person_address;
	}
	public void setPerson_address(List<Address> person_address) {
		this.person_address = person_address;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHead_img() {
		return head_img;
	}
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	public String getBasic_numbering() {
		return basic_numbering;
	}
	public void setBasic_numbering(String basic_numbering) {
		this.basic_numbering = basic_numbering;
	}
	public String getBasic_ompany() {
		return basic_ompany;
	}
	public void setBasic_ompany(String basic_ompany) {
		this.basic_ompany = basic_ompany;
	}
	public String getBasic_phone() {
		return basic_phone;
	}
	public void setBasic_phone(String basic_phone) {
		this.basic_phone = basic_phone;
	}
	public String getBasic_qq() {
		return basic_qq;
	}
	public void setBasic_qq(String basic_qq) {
		this.basic_qq = basic_qq;
	}
	public String getBasic_mail() {
		return basic_mail;
	}
	public void setBasic_mail(String basic_mail) {
		this.basic_mail = basic_mail;
	}
	public String getBasic_fax() {
		return basic_fax;
	}
	public void setBasic_fax(String basic_fax) {
		this.basic_fax = basic_fax;
	}
	public String getVat_company() {
		return vat_company;
	}
	public void setVat_company(String vat_company) {
		this.vat_company = vat_company;
	}
	public String getVat_ein() {
		return vat_ein;
	}
	public void setVat_ein(String vat_ein) {
		this.vat_ein = vat_ein;
	}
	public String getVat_address() {
		return vat_address;
	}
	public void setVat_address(String vat_address) {
		this.vat_address = vat_address;
	}
	public String getVat_opening_bank() {
		return vat_opening_bank;
	}
	public void setVat_opening_bank(String vat_opening_bank) {
		this.vat_opening_bank = vat_opening_bank;
	}
	public String getVat_phone() {
		return vat_phone;
	}
	public void setVat_phone(String vat_phone) {
		this.vat_phone = vat_phone;
	}
	public String getVat_account() {
		return vat_account;
	}
	public void setVat_account(String vat_account) {
		this.vat_account = vat_account;
	}
	public String getBilling_person() {
		return billing_person;
	}
	public void setBilling_person(String billing_person) {
		this.billing_person = billing_person;
	}
	public String getBilling_phone() {
		return billing_phone;
	}
	public void setBilling_phone(String billing_phone) {
		this.billing_phone = billing_phone;
	}
	public String getBilling_address() {
		return billing_address;
	}
	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
