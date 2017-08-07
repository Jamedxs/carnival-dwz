package com.carnival.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.carnival.util.CommonUtil;
import com.carnival.util.MemCached;
import com.carnival.util.Utils;

/**
 * @author dengxusheng
 * @create 2017年3月5日 下午10:20:18
 */
@SuppressWarnings("all")
public class DataCache extends SqlSessionDaoSupport{
	private static MemCached cache = MemCached.getInstance();
	protected WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
	protected SqlSessionTemplate sql=(SqlSessionTemplate)wac.getBean("sqlSession");
	protected SqlSession sqlSession=null;
	private static final String sqlSpaceName="Cache";

	/**
	 * ********************************************************
	 * @Title: getCacheObj
	 * @Description: TODO(获取cache整体对像)
	 * @return MemCached
	 ********************************************************
	 */
	public static MemCached getCacheObj(){
		return cache;
	}

    /**
     * ********************************************************
     * @Title: getCache
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @param cacheNames   缓存名
     * @param methodName   方法名
     * @param o
     * @param SearchKey  要搜索的键名
     * @param SearchVl   要搜索的值
     * @return Object
     ********************************************************
     */
	public Object getCache(String cacheNames,String methodName,Object o) {	
			
		Object cache_result=cache.get(cacheNames);	//cacheName的名称为AA.BB.CC时，cache_result相当于get("AA");
		if(cache_result==null){
				try {	
					Method[] ms=this.getClass().getMethods();					
				    for(Method m:ms){	
				    	 if(m.getName().equals("getCache"+methodName)){		
				    		 cache_result=m.getParameterAnnotations().length==0?m.invoke(this):m.invoke(this,o);
				    		 break;
				    	  }
				     }		
					cache.add(cacheNames, cache_result);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

	    if(cacheNames.indexOf(".")!=-1){
	    	String[] names=cacheNames.split("\\.");	
	    	try {
	        	Map<String,Object> current=(Map<String,Object>)cache_result;
		    	for(int i=1;i<names.length-1;i++){   //AA.BB.CC 从BB开始，因为cache_result就是AA的结果。
		    		if(current.get(names[i])!=null){
		    			current=(Map<String,Object>)current.get(names[i]);
		    		}else{
		    			return new HashMap<String,Object>();	
		    		}
		    	}
		    	cache_result=current.get(names[names.length-1]);
	    	} catch (Exception e) {
				e.printStackTrace();
				return new HashMap<String,Object>();
			}
	    }
		return cache_result!=null?cache_result:null;
	}

	
    /**
     * ********************************************************
     * @Title: getCache
     * @Description: TODO(根据缓存名，参数，搜索键名，搜索值获取缓存)
     * @param key  缓存名 getCache+key是方法名
     * @return Object  
     ********************************************************
     */
	public Object getCache(String key,String args,String searchKey,String searchVl){		
		String methodName=key.split("\\.")[0];		
		if(key.startsWith("b_")){  //如果是b_ 开头的，那么就是读取baseinfo的信息			
			args=methodName.replaceFirst("b_", "");
			methodName="baseinfo";			
		}		
		methodName=methodName.substring(0, 1).toUpperCase()+methodName.replaceFirst("\\w",""); 
		return this.getCache(key,methodName,args);
	}
	
	
	public Object getCache(String key,String searchKey,String searchVl){
		return this.getCache(key, null, searchKey, searchVl);
	}
	/**
	 * ********************************************************
	 * @Title: getCache
	 * @Description: TODO(根据缓存名，参数获取缓存)
	 * @param key
	 * @param args
	 * @return Object
	 ********************************************************
	 */
	public Object getCache(String key,String args){
		return this.getCache(key, args, null, null);
	}
	/**
	 * ********************************************************
	 * @Title: getCache
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @param key
	 * @return Object
	 ********************************************************
	 */
	
	public Object getCache(String key){
		return this.getCache(key, null, null, null);
	}
	public Object getDataCache(String key){
		Object obj=cache.get(key);
		if (obj==null) {
			List<Map<String,Object>> val=getSession().selectList("getDataByKeyen", key);
			if (val.size()>0) {
				Map<String,String> map=CommonUtil.listToMap(val);
				cache.add(key, map);
				obj=cache.get(key);
			}
			getSession().close();
		}
		return obj;
	}
	/**
	 * ********************************************************
	 * @Title: getCacheBaseinfo
	 * @Description: TODO(根据类CLASS_EN获取信息  )
	 * @param class_en   
	 * @return Map<String,Map<String,Object>>
	 ********************************************************
	 */
	public Map<String,Map<String,Object>> getCacheBaseinfo(String class_en){ //获取基础信息
	
        List<Map<String,Object>> l=getSession().selectList(getSqlName("baseinfo"), class_en);
      
        sqlSession.close();
		return Utils.listToMap(l, "INFO_EN");
	}
	/**
	 * ********************************************************
	 * @Title: getCacheBaseinfoClass
	 * @Description: TODO(获取所有类英文、中文信息)
	 * @return Map<String,String>
	 ********************************************************
	 */
	public Map<String,String> getCacheBaseinfoClass(){  //获取基础信息,<类英文，类中文>
		List<Map<String,Object>> l=getSession().selectList(getSqlName("baseinfoClass"));
		sqlSession.close();
        return  Utils.listToMap(l, "CLASS_EN","CLASS_CN");
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getCacheCarte <br>
	 * [描述] 获取菜单map <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Map<String,Map<String,Object>> <br>
	 *********************************************************.<br>
	 */
	public List<Map<String, Object>> getCacheCarte(){
		List<Map<String, Object>> carteList = getSession().selectList(getSqlName("getAllList"));
		closeSession();
		return carteList;
	}
	public Map<String, Map<String, Object>> getCacheBackCarte(){
		List<Map<String, Object>> carteList = getSession().selectList(getSqlName("getAllList"));
		closeSession();
		Map<String, Map<String, Object>> IdListMap=CommonUtil.listToMap(carteList, "ID");
		return IdListMap;
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getMenuCarte <br>
	 * [描述] 获取用户菜单 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] List<Map<String,Object>> <br>
	 *********************************************************.<br>
	 */
	public List<Map<String, Object>> getCacheMenuCarte(){
//		Users users = new BaseController().getUsers();
//		Integer admin_id =  users.getId();
		String sql = "select * from carte where 1=1";
//		if(users.getIsadmin()==1){
//			sql += " order by parent_id ";
//		}else{
//			sql += " and id in (select carte_id from userallot where admin_id = "+admin_id+") order by parent_id ";
//		}
		List<Map<String, Object>> carteList = getSession().selectList(getSqlName("selectUserCarte"), sql);
		return carteList;
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getCacheBtnCache <br>
	 * [描述] 获取按钮数据 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] List<Map<String,Object>> <br>
	 *********************************************************.<br>
	 */
	public List<Map<String, Object>> getCacheBtnCache(){
//		Users users = new BaseController().getUsers();
//		Integer admin_id =  users.getId();
		String sql = "select carte_id,button_name,href from permission where status = 1";
//		if(users.getIsadmin()!=1){
//			sql += " and id in (select per_id from permission_mapping where admin_id = "+admin_id+") ";
//		}
		List<Map<String, Object>> btnList = getSession().selectList(getSqlName("selectUserBtn"), sql);
		return btnList;
	}
	/**
	 * ********************************************************
	 * @Title: getCacheSystem
	 * @Description: TODO(获取所有只有一个值的类信息)info_num=-1
	 * @return Map<String,String>
	 ********************************************************
	 */
	public Map<String,String> getCacheSystem(){        //获取系统信息（即基本信息类就一个值 的）
		List<Map<String,Object>> l=getSession().selectList(getSqlName("system"));
		sqlSession.close();
        return  Utils.listToMap(l, "CLASS_EN","VL");
	}
	
	/**
	 * ********************************************************
	 * @Title: getSession
	 * @Description: TODO(获取sqlSession并打开)
	 * @return SqlSession
	 ********************************************************
	 */
	public SqlSession getSession(){
		this.sqlSession=sql.getSqlSessionFactory().openSession();
		return sqlSession;
	}
	/**
	 * ********************************************************
	 * @Title: closeSession
	 * @Description: TODO(关闭数据连接) void
	 ********************************************************
	 */
	public void closeSession(){
		sqlSession.close();
	}
	/**
	 * ********************************************************
	 * @Title: getSqlName
	 * @Description: TODO(获取执行SQL所需要的格式： 命令空间.SQLID)
	 * @param sqlId
	 * @return String
	 ********************************************************
	 */
	public String getSqlName(String sqlId){
		 return sqlSpaceName+"."+sqlId;
	}
   
	
}
