
package com.carnival.service.impl;

import com.carnival.entity.BaseinfoKey;
import com.carnival.dao.BaseinfoKeyDao;
import com.carnival.service.BaseinfoKeyService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* ********************************************************
* @ClassName: BaseinfoKeyServiceImpl
* @Description: 基础数据(KEY)
* @author 自动生成
* @date 2017-03-07 下午 03:29:08 
*******************************************************
*/
@Service
public class BaseinfoKeyServiceImpl extends BaseServiceImpl<BaseinfoKey,Integer> implements BaseinfoKeyService{

	@Resource
	private BaseinfoKeyDao baseinfoKeyDao;

	@Resource
	public void setBaseDao(BaseinfoKeyDao bDao) {
		super.setBaseDao(baseinfoKeyDao);
	}
}

