
package com.carnival.entity;

import java.util.Date;

/**
* ********************************************************
* @ClassName: BaseinfoKey
* @Description: 基础数据(KEY)
* @author 自动生成
* @date 2017-03-07 下午 03:29:08 
*******************************************************
*/
public class BaseinfoKey {

	private Integer id;		//
	private String addtime;		//添加时间
	private String parent_key_name;		//父类KEY的名称
	private String key_name;		//KEY名称
	private String remark;		//备注
	private Integer status;		//0-启用，1-禁用
	private String key_value;		//值(内容)
	private String parent_key_en;		//父类KEY标识
	private String addname;		//添加人
	private String key_en;		//KEY的英文标识
	private Integer vl_num;		//值个数(0-无，1-一个，2-多个)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getParent_key_name() {
		return this.parent_key_name;
	}

	public void setParent_key_name(String parent_key_name) {
		this.parent_key_name = parent_key_name;
	}

	public String getKey_name() {
		return this.key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getKey_value() {
		return this.key_value;
	}

	public void setKey_value(String key_value) {
		this.key_value = key_value;
	}

	public String getParent_key_en() {
		return this.parent_key_en;
	}

	public void setParent_key_en(String parent_key_en) {
		this.parent_key_en = parent_key_en;
	}

	public String getAddname() {
		return this.addname;
	}

	public void setAddname(String addname) {
		this.addname = addname;
	}

	public String getKey_en() {
		return this.key_en;
	}

	public void setKey_en(String key_en) {
		this.key_en = key_en;
	}

	public Integer getVl_num() {
		return this.vl_num;
	}

	public void setVl_num(Integer vl_num) {
		this.vl_num = vl_num;
	}

}

