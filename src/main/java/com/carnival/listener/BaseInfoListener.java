package com.carnival.listener;

import java.util.ArrayList;
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

/**
 * @author dengxusheng
 * @create 2017年3月5日 下午10:10:06
 */
@SuppressWarnings("all")
public class BaseInfoListener extends SqlSessionDaoSupport{

	private static MemCached cache = MemCached.getInstance();
	protected WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
	protected SqlSessionTemplate sql=(SqlSessionTemplate)wac.getBean("sqlSession");
	protected SqlSession sqlSession=null;
	
	public BaseInfoListener(){
		
	}
	public void BaseInfoCache(){
		List<Map<String,Object>> keyList=getSession().selectList("getKeyOneAndTwo");
		List<Map<String,Object>> valList=getSession().selectList("getAllListOrderByKey");
		getSession().close();
		for (int i = 0; i < keyList.size(); i++) {
			Map<String,Object> keyMap=(Map<String,Object>)keyList.get(i);
			String vl_num=keyMap.get("VL_NUM")+"";
			String key_en=keyMap.get("KEY_EN")+"";
			if ("1".equals(vl_num)) {
				Map<String,String> map=new HashMap<String,String>();
				map.put(key_en, keyMap.get("KEY_VALUE")+"");
			}else if ("2".equals(vl_num)) {
				List<Map<String,Object>> val=new ArrayList<Map<String,Object>>();
				for (int j = 0; j < valList.size(); j++) {
					if (key_en.equals(valList.get(j).get("KEY_EN"))) {
						Map<String,Object> vm=valList.get(j);
						val.add(vm);
					}
				}
				Map<String,String> map=CommonUtil.listToMap(val);
				cache.add(key_en, map);
			}
		}
	}
	
	public SqlSession getSession(){
		sqlSession=sql.getSqlSessionFactory().openSession();
		return sqlSession;
	}
}
