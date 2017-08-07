
package com.carnival.service.impl;

import com.carnival.entity.PermissionMapping;
import com.carnival.dao.PermissionMappingDao;
import com.carnival.service.PermissionMappingService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* ********************************************************
* @ClassName: PermissionMappingServiceImpl
* @Description: 按钮分配
* @author 自动生成
* @date 2017-03-07 下午 03:30:06 
*******************************************************
*/
@Service
public class PermissionMappingServiceImpl extends BaseServiceImpl<PermissionMapping,Integer> implements PermissionMappingService{

	@Resource
	private PermissionMappingDao permissionMappingDao;

	@Resource
	public void setBaseDao(PermissionMappingDao pDao) {
		super.setBaseDao(permissionMappingDao);
	}
}

