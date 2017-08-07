package com.carnival.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.carnival.service.BaseService;
import com.carnival.dao.BaseDao;
import com.carnival.entity.Page;

/**
 * @author dengxusheng
 * @create 2017年3月5日 下午9:29:16
 */
@Service
@SuppressWarnings("all")
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {
	
	private BaseDao<T, PK> baseDao;	
	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}
	/**------------------insert ↓----------------------------*/
	public int insert(T obj){
		return baseDao.insert(obj);
	}
	
	public int insert(String statement,Object parameter){
		return baseDao.insert(statement, parameter);
	}
	
	public int insert(String statement){
		return baseDao.insert(statement);
	}
	/**------------------update ↓----------------------------*/
	public int update(T obj){
		return baseDao.update(obj);
	}
	
	public int update(String statement,Object parameter){
		return baseDao.update(statement, parameter);
	}
	
	public int update(String statement){
		return baseDao.update(statement);
	}
	/**------------------delete ↓----------------------------*/
	public int delete(PK id){
		return baseDao.delete(id);
	}
	
	public int delete(String statement,Object parameter){
		return baseDao.delete(statement, parameter);
	}
	
	public int delete(String statement){
		return baseDao.delete(statement);
	}
	/**----------------------list-----------------------*/
	public void getPageList(Page p){
		baseDao.getPageList(p);
	}
	
	public void getPageList(String statement,Page p){
		baseDao.getPageList(statement, p);
	}
	
	public List<Map<String,Object>> getList(Object parameter) {//获取分析不分页LIST
		return baseDao.getList(parameter);
	}
	
	public List<Map<String,Object>> getList(String statement,Object parameter){
		return baseDao.getList(statement,parameter);
	}
	
	public List getObjectList(String statement){//获取分析不分页LIST，返回Object list
		return baseDao.getObjectList(statement);
	}
	
	public List getObjectList(String statement,Object parameter){
		return baseDao.getObjectList(statement, parameter);
	}
	/**----------------------select one-----------------------*/
	
	public T getOne(Object parameter){
		return baseDao.getOne(parameter);
	}
	public T getOne(String statement,Object parameter){
		return baseDao.getOne(statement,parameter);
	}
	
	/**---------------------select  number------------------*/
	public Integer getNumber(String statement){
		return baseDao.getNumber(statement);
	}
	
	public Integer getNumber(String statement,Object parameter){
		return baseDao.getNumber(statement,parameter);
	}

	public List<Map<String, Object>> getList(String statement) {
		return baseDao.getList(statement);
	}
	/**---------------------select  String------------------*/

	public String getString(String statement) {
		// TODO Auto-generated method stub
		return baseDao.getString(statement);
	}

	public String getString(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return baseDao.getString(statement,parameter);
	}
}
