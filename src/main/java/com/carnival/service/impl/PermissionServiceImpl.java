
package com.carnival.service.impl;

import com.carnival.entity.Permission;
import com.carnival.dao.PermissionDao;
import com.carnival.service.PermissionService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* ********************************************************
* @ClassName: PermissionServiceImpl
* @Description: 按钮表
* @author 自动生成
* @date 2017-03-07 下午 03:29:56 
*******************************************************
*/
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,Integer> implements PermissionService{

	@Resource
	private PermissionDao permissionDao;

	@Resource
	public void setBaseDao(PermissionDao pDao) {
		super.setBaseDao(permissionDao);
	}
}

