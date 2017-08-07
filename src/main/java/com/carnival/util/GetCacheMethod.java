package com.carnival.util;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.carnival.util.DataCache;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author dengxusheng
 * @create 2017年3月5日 下午10:22:30
 */
@SuppressWarnings("all")
public class GetCacheMethod implements TemplateMethodModel {  
      
    
 
      
    /** 
     * 带参数的构造函数 
     * @param response HttpServletResponse对象 
     */  
    public GetCacheMethod(HttpServletResponse response)  
    {  
    }  

    /** 
     * 执行方法 
     * @param argList 方法参数列表 
     * @return Object 方法返回值 
     * @throws TemplateModelException 
     */  
    public Object exec(List argList) throws TemplateModelException { 
    	DataCache dc=new DataCache();
    	int argsize=argList.size();
    	switch(argsize){
    	  case 1:
    		  return dc.getDataCache((String)argList.get(0)); 
    	  case 2:
    		  return dc.getCache((String)argList.get(0),(String)argList.get(1)); 
    	  case 3:
    		  return dc.getCache((String)argList.get(0),(String)argList.get(1),(String)argList.get(2)); 
    	  case 4:
    		  return dc.getCache((String)argList.get(0),(String)argList.get(1),(String)argList.get(2),(String)argList.get(3));
    	}   
        throw new TemplateModelException("Wrong arguments!");       
    }  
}  
