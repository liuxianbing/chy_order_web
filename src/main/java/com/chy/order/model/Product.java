package com.chy.order.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;

/** 
* @author liuxianbing: 
* @version 创建时间：2017年12月11日 下午12:37:53 
* 类说明 
*/
public class Product implements Serializable{

	private static final long serialVersionUID = -8960856764295624629L;
	private Long id,uid;
	private String product_id;
	private String product_time;
	private String product_name;
	private String product_number;
	private String product_type;
	private String pcba_process;
	private String pcba_smt_type;
	private String pcba_smt_joints;
	private String pcba_dip_type,pcba_dip_joints,pcba_stencil,pcba_stencil_num,pcb_cmstart,pcb_cmstop;
	private Integer pcb_layer,product_stat;
	private Double pcb_thickness;
	private String pcb_spray,pcb_solder,remark,product_type_number,test_time,assembly_time,prevent_cm2,product_class_type,now_time;
	private String create_time,pcb_file,coordinate_file,process_file,bom_shopfile;
	
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Integer getProduct_stat() {
		return product_stat;
	}
	public void setProduct_stat(Integer product_stat) {
		this.product_stat = product_stat;
	}
	public String getBom_shopfile() {
		return bom_shopfile;
	}
	public void setBom_shopfile(String bom_shopfile) {
		this.bom_shopfile = bom_shopfile;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_time() {
		return product_time;
	}
	public void setProduct_time(String product_time) {
		this.product_time = product_time;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_number() {
		return product_number;
	}
	public void setProduct_number(String product_number) {
		this.product_number = product_number;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getPcba_process() {
		return pcba_process;
	}
	public void setPcba_process(String pcba_process) {
		this.pcba_process = pcba_process;
	}
	public String getPcba_smt_type() {
		return pcba_smt_type;
	}
	public void setPcba_smt_type(String pcba_smt_type) {
		this.pcba_smt_type = pcba_smt_type;
	}
	public String getPcba_smt_joints() {
		return pcba_smt_joints;
	}
	public void setPcba_smt_joints(String pcba_smt_joints) {
		this.pcba_smt_joints = pcba_smt_joints;
	}
	public String getPcba_dip_type() {
		return pcba_dip_type;
	}
	public void setPcba_dip_type(String pcba_dip_type) {
		this.pcba_dip_type = pcba_dip_type;
	}
	public String getPcba_dip_joints() {
		return pcba_dip_joints;
	}
	public void setPcba_dip_joints(String pcba_dip_joints) {
		this.pcba_dip_joints = pcba_dip_joints;
	}
	public String getPcba_stencil() {
		return pcba_stencil;
	}
	public void setPcba_stencil(String pcba_stencil) {
		this.pcba_stencil = pcba_stencil;
	}
	public String getPcba_stencil_num() {
		return pcba_stencil_num;
	}
	public void setPcba_stencil_num(String pcba_stencil_num) {
		this.pcba_stencil_num = pcba_stencil_num;
	}
	public String getPcb_cmstart() {
		return pcb_cmstart;
	}
	public void setPcb_cmstart(String pcb_cmstart) {
		this.pcb_cmstart = pcb_cmstart;
	}
	public String getPcb_cmstop() {
		return pcb_cmstop;
	}
	public void setPcb_cmstop(String pcb_cmstop) {
		this.pcb_cmstop = pcb_cmstop;
	}
	public Integer getPcb_layer() {
		return pcb_layer;
	}
	public void setPcb_layer(Integer pcb_layer) {
		this.pcb_layer = pcb_layer;
	}
	public Double getPcb_thickness() {
		return pcb_thickness;
	}
	public void setPcb_thickness(Double pcb_thickness) {
		this.pcb_thickness = pcb_thickness;
	}
	public String getPcb_spray() {
		return pcb_spray;
	}
	public void setPcb_spray(String pcb_spray) {
		this.pcb_spray = pcb_spray;
	}
	public String getPcb_solder() {
		return pcb_solder;
	}
	public void setPcb_solder(String pcb_solder) {
		this.pcb_solder = pcb_solder;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProduct_type_number() {
		return product_type_number;
	}
	public void setProduct_type_number(String product_type_number) {
		this.product_type_number = product_type_number;
	}
	public String getTest_time() {
		return test_time;
	}
	public void setTest_time(String test_time) {
		this.test_time = test_time;
	}
	public String getAssembly_time() {
		return assembly_time;
	}
	public void setAssembly_time(String assembly_time) {
		this.assembly_time = assembly_time;
	}
	public String getPrevent_cm2() {
		return prevent_cm2;
	}
	public void setPrevent_cm2(String prevent_cm2) {
		this.prevent_cm2 = prevent_cm2;
	}
	public String getProduct_class_type() {
		return product_class_type;
	}
	public void setProduct_class_type(String product_class_type) {
		this.product_class_type = product_class_type;
	}
	public String getNow_time() {
		return now_time;
	}
	public void setNow_time(String now_time) {
		this.now_time = now_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getPcb_file() {
		return pcb_file;
	}
	public void setPcb_file(String pcb_file) {
		this.pcb_file = pcb_file;
	}
	public String getCoordinate_file() {
		return coordinate_file;
	}
	public void setCoordinate_file(String coordinate_file) {
		this.coordinate_file = coordinate_file;
	}
	public String getProcess_file() {
		return process_file;
	}
	public void setProcess_file(String process_file) {
		this.process_file = process_file;
	}
	
	
}
