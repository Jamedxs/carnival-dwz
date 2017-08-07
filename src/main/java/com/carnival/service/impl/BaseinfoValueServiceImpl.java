
package com.carnival.service.impl;

import com.carnival.entity.BaseinfoValue;
import com.carnival.dao.BaseinfoValueDao;
import com.carnival.service.BaseinfoValueService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* ********************************************************
* @ClassName: BaseinfoValueServiceImpl
* @Description: 基础数据VALUE表
* @author 自动生成
* @date 2017-03-07 下午 03:29:19 
*******************************************************
*/
@Service
public class BaseinfoValueServiceImpl extends BaseServiceImpl<BaseinfoValue,Integer> implements BaseinfoValueService{

	@Resource
	private BaseinfoValueDao baseinfoValueDao;

	@Resource
	public void setBaseDao(BaseinfoValueDao bDao) {
		super.setBaseDao(baseinfoValueDao);
	}
}

