
package com.carnival.service.impl;

import com.carnival.entity.SystemNotification;
import com.carnival.dao.SystemNotificationDao;
import com.carnival.service.SystemNotificationService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* ********************************************************
* @ClassName: SystemNotificationServiceImpl
* @Description: 系统通知表
* @author 自动生成
* @date 2017-03-07 下午 03:30:18 
*******************************************************
*/
@Service
public class SystemNotificationServiceImpl extends BaseServiceImpl<SystemNotification,Integer> implements SystemNotificationService{

	@Resource
	private SystemNotificationDao systemNotificationDao;

	@Resource
	public void setBaseDao(SystemNotificationDao sDao) {
		super.setBaseDao(systemNotificationDao);
	}
}

