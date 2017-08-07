
package com.carnival.service.impl;

import com.carnival.entity.BackCarte;
import com.carnival.dao.BackCarteDao;
import com.carnival.service.BackCarteService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* ********************************************************
* @ClassName: BackCarteServiceImpl
* @Description: 菜单表
* @author 自动生成
* @date 2017-03-07 下午 03:29:47 
*******************************************************
*/
@Service
public class BackCarteServiceImpl extends BaseServiceImpl<BackCarte,Integer> implements BackCarteService{

	@Resource
	private BackCarteDao backCarteDao;

	@Resource
	public void setBaseDao(BackCarteDao bDao) {
		super.setBaseDao(backCarteDao);
	}
}

