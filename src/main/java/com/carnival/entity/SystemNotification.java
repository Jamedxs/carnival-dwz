
package com.carnival.entity;

import java.util.Date;

/**
* ********************************************************
* @ClassName: SystemNotification
* @Description: 系统通知表
* @author 自动生成
* @date 2017-03-07 下午 03:30:18 
*******************************************************
*/
public class SystemNotification {

	private Integer id;		//编号
	private String add_man;		//发布人
	private String theme;		//标题
	private Date create_time;		//发布时间
	private String note;		//内容

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdd_man() {
		return this.add_man;
	}

	public void setAdd_man(String add_man) {
		this.add_man = add_man;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Date getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}

