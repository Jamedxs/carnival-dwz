
package com.carnival.entity;

import java.util.Date;

/**
* ********************************************************
* @ClassName: LoginRecord
* @Description: 登录记录表
* @author 自动生成
* @date 2017-03-07 下午 03:29:34 
*******************************************************
*/
public class LoginRecord {

	private Integer id;		//编号
	private String enterprise_no;		//所属企业编号
	private String user_name;		//登录名
	private Date login_time;		//登录时间
	private Integer login_state;		//登录状态0成功1失败
	private String real_name;		//姓名
	private String login_ip;		//登录ip
	private Integer note;		//内容0成功1密码错误2验证码错误
	private Date login_date;		//登录日期

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnterprise_no() {
		return this.enterprise_no;
	}

	public void setEnterprise_no(String enterprise_no) {
		this.enterprise_no = enterprise_no;
	}

	public String getUser_name() {
		return this.user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getLogin_time() {
		return this.login_time;
	}

	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}

	public Integer getLogin_state() {
		return this.login_state;
	}

	public void setLogin_state(Integer login_state) {
		this.login_state = login_state;
	}

	public String getReal_name() {
		return this.real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getLogin_ip() {
		return this.login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public Integer getNote() {
		return this.note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public Date getLogin_date() {
		return this.login_date;
	}

	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}

}

