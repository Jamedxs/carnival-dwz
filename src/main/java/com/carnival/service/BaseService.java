package com.carnival.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.carnival.entity.Page;

/**
 * @author dengxusheng
 * @create 2017年3月5日 下午9:27:45
 */
@SuppressWarnings("all")
public interface BaseService<T,PK extends Serializable> {
	public int insert(T obj);
	public int insert(String statement,Object parameter);
	public int insert(String statement);
	
	public int update(T obj);
	public int update(String statement,Object parameter);
	public int update(String statement);
	
	public int delete(PK id);
	public int delete(String statement,Object parameter);
	public int delete(String statement);
	
	public void getPageList(Page p);
	public void getPageList(String statement,Page p);
	public List<Map<String,Object>> getList(String statement);
	public List<Map<String,Object>> getList(Object parameter);//获取分析不分页LIST
	public List<Map<String,Object>> getList(String statement,Object parameter);
	public List getObjectList(String statement);//获取分析不分页LIST，返回Object list
	public List getObjectList(String statement,Object parameter);
	
	public T getOne(Object parameter);
	public T getOne(String statement,Object parameter);
	
	public Integer getNumber(String statement);
	public Integer getNumber(String statement,Object parameter);
	
	public String  getString(String statement);
	public String  getString(String statement,Object parameter);
	
}
