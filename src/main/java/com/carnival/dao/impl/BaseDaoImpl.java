package com.carnival.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.carnival.dao.BaseDao;
import com.carnival.entity.Page;

/**
 * @author dengxusheng
 * @create 2017年3月5日 下午9:21:23
 */
public class BaseDaoImpl <T,PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<T,PK> {
	public  static final String SQLNAME_SEPARATOR =  ".";
	public  static final String SQL_INSERT        =  "insert";
	public  static final String SQL_UPDATE        =  "update";
	public  static final String SQL_DELETE        =  "delete";
	public  static final String SQL_GETBYID       =  "getById";
	public  static final String SQL_GETPAGELIST   =  "getPageList";	
	public  static final String SQL_GETLIST       =  "getList";	
	public  static final String SQL_GETONE        =  "getOne";	
	private              String sqlNamespace      =  this.getNamespace();
	/**--------------------------------------------start---set-get---------------------**/
	public String getSqlNamespace() {
		return sqlNamespace;
	}

	public void setSqlNamespace(String sqlNamespace) {
		this.sqlNamespace = sqlNamespace;
	}
	/**--------------------------------------------end---set-get---------------------**/
  
	@SuppressWarnings("unchecked")
	public String getNamespace() {	
		Class<T> clazz =null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            clazz = (Class<T>) parameterizedType[0];           
            return clazz.getSimpleName();          
        }
        return null;
	}
	
	/**
	 * 
	 * @param sqlName
	 * @return
	 */
	protected String getSqlName(String sqlName) {
		return sqlNamespace + SQLNAME_SEPARATOR + sqlName;
	}
	/**----------------------------------insert -----------------------------------------**/
	/**
	 * 
	 * @param obj
	 * @return int 
	 */
	public int insert(T obj){
		return this.getSqlSession().insert(getSqlName(SQL_INSERT), obj);
	}
	/**
	 * 
	 * @param statement 
	 * @param parameter  
	 * @return  
	 */
	public int insert(String statement, Object parameter) {
		return this.getSqlSession().insert(getSqlName(statement), parameter);
	}
	/**
	 *	 * @param statement
	 * @return
	 */
	public int insert(String statement) {	
		return this.getSqlSession().insert(getSqlName(statement));
	}	
	/**----------------------------------update -----------------------------------------**/
	/**
	 * 
	 * @param obj
	 * @return 
	 */
	public int update(T obj){
		return this.getSqlSession().update(getSqlName(SQL_UPDATE), obj);
	}
	/**
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int update(String statement, Object parameter) {
		return this.getSqlSession().update(getSqlName(statement), parameter);
	} 
	/**
	 * 
	 * @param statement
	 * @return
	 */
	public int update(String statement) {
		return this.getSqlSession().update(getSqlName(statement));
	} 
	/**----------------------------------delete -----------------------------------------**/
	/**
	 * 
	 * @param id
	 * @return 
	 */
	public int delete(PK id){
		return this.getSqlSession().delete(getSqlName(SQL_DELETE), id);
	}
	/**
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int delete(String statement,Object parameter){
		return this.getSqlSession().delete(getSqlName(statement), parameter);
	}
	/**
	 * 
	 * @param statement
	 * @return
	 */
	public int delete(String statement){
		return this.getSqlSession().delete(statement);
	}
	/**----------------------------------select-----------------------------------------**/
	
	/**------select list-----**/	
	/**
	 * @param Page p
	 * @return List 
	 */
	public void getPageList(Page p){
		p.setResults(this.getSqlSession().selectList(getSqlName(SQL_GETPAGELIST), p));		
	}
	
	public void getPageList(String statement, Page p) {
		p.setResults(this.getSqlSession().selectList(getSqlName(statement), p));		
	}
	
	
	public List<Map<String,Object>> getList(Object parameter){
		return (List<Map<String,Object>>)this.getSqlSession().selectList(getSqlName(this.SQL_GETLIST), parameter);
	}
	
	public List<Map<String, Object>> getList(String statement,Object parameter){
		return (List<Map<String,Object>>)this.getSqlSession().selectList(getSqlName(statement), parameter);
	}
	
	public List getObjectList(String statement){
			return this.getSqlSession().selectList(statement);
	}
	public List getObjectList(String statement,Object parameter){
		   return this.getSqlSession().selectList(statement,parameter);
	}

	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public T getOne(Object parameter){
		return (T) this.getSqlSession().selectOne(getSqlName(SQL_GETONE),parameter);
	}
	@SuppressWarnings("unchecked")
	public T getOne(String statement, Object parameter) {
		return (T) this.getSqlSession().selectOne(getSqlName(statement),parameter);
	}
	
	
	/**
	 * 
	 */
	public Integer getNumber(String statement,Object parameter){

		return (Integer) this.getSqlSession().selectOne(statement,parameter);
	}
	
	public Integer getNumber(String statement){
		return (Integer) this.getSqlSession().selectOne(statement);
	}

	public List<Map<String,Object>> getList(String statement) {
		List<Map<String,Object>> list=null;
		try {
			list=this.getSqlSession().selectList(getSqlName(statement));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public String getString(String statement, Object parameter) {
		return (String) this.getSqlSession().selectOne(statement,parameter);
	}

	public String getString(String statement) {
		// TODO Auto-generated method stub
		return (String) this.getSqlSession().selectOne(statement);
	}

}
