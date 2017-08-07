
package com.carnival.entity;

import java.util.Date;

/**
* ********************************************************
* @ClassName: Permission
* @Description: 按钮表
* @author 自动生成
* @date 2017-03-07 下午 03:29:56 
*******************************************************
*/
public class Permission {

	private Integer id;		//主键
	private String action_class;		//类名
	private String lower_href;		//表单地址
	private Integer status;		//状态(0-启用，1-禁用)
	private String behavior_args;		//行为模板
	private String behavior;		//行为名称
	private Integer carte_id;		//所属菜单
	private String create_time;		//添加时间
	private String button_name;		//按钮名称
	private String href;		//按钮路径
	private String action_method;		//方法名称

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction_class() {
		return this.action_class;
	}

	public void setAction_class(String action_class) {
		this.action_class = action_class;
	}

	public String getLower_href() {
		return this.lower_href;
	}

	public void setLower_href(String lower_href) {
		this.lower_href = lower_href;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBehavior_args() {
		return this.behavior_args;
	}

	public void setBehavior_args(String behavior_args) {
		this.behavior_args = behavior_args;
	}

	public String getBehavior() {
		return this.behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public Integer getCarte_id() {
		return this.carte_id;
	}

	public void setCarte_id(Integer carte_id) {
		this.carte_id = carte_id;
	}

	public String getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getButton_name() {
		return this.button_name;
	}

	public void setButton_name(String button_name) {
		this.button_name = button_name;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getAction_method() {
		return this.action_method;
	}

	public void setAction_method(String action_method) {
		this.action_method = action_method;
	}

}

