
package com.carnival.entity;

import java.util.Date;

/**
* ********************************************************
* @ClassName: BaseinfoValue
* @Description: 基础数据VALUE表
* @author 自动生成
* @date 2017-03-07 下午 03:29:19 
*******************************************************
*/
public class BaseinfoValue {

	private Integer id;		//
	private String value_content;		//内容
	private String value_name;		//名称
	private String addname;		//添加人
	private String key_en;		//所属类型
	private String value_en;		//英文标识

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue_content() {
		return this.value_content;
	}

	public void setValue_content(String value_content) {
		this.value_content = value_content;
	}

	public String getValue_name() {
		return this.value_name;
	}

	public void setValue_name(String value_name) {
		this.value_name = value_name;
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

	public String getValue_en() {
		return this.value_en;
	}

	public void setValue_en(String value_en) {
		this.value_en = value_en;
	}

}

