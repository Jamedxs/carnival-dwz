
package com.carnival.service.impl;

import com.carnival.entity.AdminInfo;
import com.carnival.dao.AdminInfoDao;
import com.carnival.service.AdminInfoService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* ********************************************************
* @ClassName: AdminInfoServiceImpl
* @Description: 用户表
* @author 自动生成
* @date 2017-03-07 下午 03:28:34 
*******************************************************
*/
@Service
public class AdminInfoServiceImpl extends BaseServiceImpl<AdminInfo,Integer> implements AdminInfoService{

	@Resource
	private AdminInfoDao adminInfoDao;

	@Resource
	public void setBaseDao(AdminInfoDao aDao) {
		super.setBaseDao(adminInfoDao);
	}
}

