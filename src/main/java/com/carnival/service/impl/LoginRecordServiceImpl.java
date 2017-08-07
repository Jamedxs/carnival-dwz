
package com.carnival.service.impl;

import com.carnival.entity.LoginRecord;
import com.carnival.dao.LoginRecordDao;
import com.carnival.service.LoginRecordService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* ********************************************************
* @ClassName: LoginRecordServiceImpl
* @Description: 登录记录表
* @author 自动生成
* @date 2017-03-07 下午 03:29:34 
*******************************************************
*/
@Service
public class LoginRecordServiceImpl extends BaseServiceImpl<LoginRecord,Integer> implements LoginRecordService{

	@Resource
	private LoginRecordDao loginRecordDao;

	@Resource
	public void setBaseDao(LoginRecordDao lDao) {
		super.setBaseDao(loginRecordDao);
	}
}

