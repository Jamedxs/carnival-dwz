
package com.carnival.entity;

import java.util.Date;

/**
* ********************************************************
* @ClassName: PermissionMapping
* @Description: 按钮分配
* @author 自动生成
* @date 2017-03-07 下午 03:30:06 
*******************************************************
*/
public class PermissionMapping {

	private Integer id;		//主键
	private Integer admin_id;		//用户ID
	private Integer per_id;		//按钮ID

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdmin_id() {
		return this.admin_id;
	}

	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}

	public Integer getPer_id() {
		return this.per_id;
	}

	public void setPer_id(Integer per_id) {
		this.per_id = per_id;
	}

}

