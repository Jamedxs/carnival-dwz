
package com.carnival.entity;

import java.util.Date;

/**
* ********************************************************
* @ClassName: AdminInfo
* @Description: 用户表
* @author 自动生成
* @date 2017-03-07 下午 03:28:34 
*******************************************************
*/
public class AdminInfo {

	private String enterprise_no;		//所属企业编号
	private Integer belong_platform;		//0-企业,1-总后台,2机构，3.银行
	private String phone;		//手机号
	private String admin_realname;		//真实姓名
	private Integer status;		//0-启用，1-停用,2-冻结
	private String admin_name;		//
	private String login_ip;		//登录ip
	private String userno;		//用户编号
	private String pass;		//
	private Integer id;		//
	private String login_time;		//登录时间
	private Integer belong_role;		//0-普通角色，1-管理员
	private String add_time;		//注册日期
	private Integer error_count;		//登陆错误次数

	public String getEnterprise_no() {
		return this.enterprise_no;
	}

	public void setEnterprise_no(String enterprise_no) {
		this.enterprise_no = enterprise_no;
	}

	public Integer getBelong_platform() {
		return this.belong_platform;
	}

	public void setBelong_platform(Integer belong_platform) {
		this.belong_platform = belong_platform;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdmin_realname() {
		return this.admin_realname;
	}

	public void setAdmin_realname(String admin_realname) {
		this.admin_realname = admin_realname;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAdmin_name() {
		return this.admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getLogin_ip() {
		return this.login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public String getUserno() {
		return this.userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin_time() {
		return this.login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public Integer getBelong_role() {
		return this.belong_role;
	}

	public void setBelong_role(Integer belong_role) {
		this.belong_role = belong_role;
	}

	public String getAdd_time() {
		return this.add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public Integer getError_count() {
		return this.error_count;
	}

	public void setError_count(Integer error_count) {
		this.error_count = error_count;
	}

}

