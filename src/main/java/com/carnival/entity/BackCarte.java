
package com.carnival.entity;

import java.util.Date;

/**
* ********************************************************
* @ClassName: BackCarte
* @Description: 菜单表
* @author 自动生成
* @date 2017-03-07 下午 03:29:47 
*******************************************************
*/
public class BackCarte {

	private Integer id;		//
	private Integer belong_platform;		//0-企业端,1-总后台,2银行端
	private String parents;		//父菜单标识
	private String carte_intro;		//菜单介绍
	private Integer levels;		//菜单等级
	private String carte_name;		//菜单名称
	private String target;		//打开方式
	private String rel;		//REL属性
	private String href;		//URL地址
	private String parent_id;		//菜单标识

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBelong_platform() {
		return this.belong_platform;
	}

	public void setBelong_platform(Integer belong_platform) {
		this.belong_platform = belong_platform;
	}

	public String getParents() {
		return this.parents;
	}

	public void setParents(String parents) {
		this.parents = parents;
	}

	public String getCarte_intro() {
		return this.carte_intro;
	}

	public void setCarte_intro(String carte_intro) {
		this.carte_intro = carte_intro;
	}

	public Integer getLevels() {
		return this.levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public String getCarte_name() {
		return this.carte_name;
	}

	public void setCarte_name(String carte_name) {
		this.carte_name = carte_name;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRel() {
		return this.rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getParent_id() {
		return this.parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

}

