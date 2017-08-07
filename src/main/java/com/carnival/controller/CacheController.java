package com.carnival.controller;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carnival.controller.BaseController;
import com.carnival.util.DataCache;
import com.carnival.util.MemCached;
import com.carnival.util.Utils;

/**
 * @author dengxusheng
 * @create 2017年3月9日 上午11:09:11
 */
@Scope("prototype")
@Controller
@RequestMapping("/Cache")
@SuppressWarnings("all")
public class CacheController extends BaseController{
	 protected DataCache dc=new DataCache();
	  /**
	   * ********************************************************
	   * @Title: clearCache
	   * @Description: TODO(清除所有缓存)
	   * @return Map
	   * @date 
	   ********************************************************
	   */
	  @RequestMapping("/clearCache")
      public @ResponseBody Map clearCache(){		 
//		  dc.getCacheObj().flushAll();
		  MemCached.getInstance().delete("backcarte1");
		  MemCached.getInstance().delete("backcarteList1");
    	  return success("删除缓存成功","");
      }
	  /**
	   * ********************************************************
	   * @Title: ajaxGetCache
	   * @Description: TODO(这里用一句话描述这个类的作用)
	   * @return Map
	   * @date 
	   ********************************************************
	   */
	  @RequestMapping("/ajaxGetCache")
	  public @ResponseBody Map ajaxGetCache(){		 
		  String key=request.getParameter("key")==null?null:Utils.getUtf8(request.getParameter("key"));
		  String args=request.getParameter("args")==null?null:Utils.getUtf8(request.getParameter("args"));
		  String searchKey=request.getParameter("searchKey")==null?null:Utils.getUtf8(request.getParameter("searchKey"));
		  String searchVl=request.getParameter("searchVl")==null?null:Utils.getUtf8(request.getParameter("searchVl"));   
		  if(key==null||key.equals(""))return null;
		  return (Map)dc.getCache(key, args, searchKey, searchVl);		  
	  }
	

}
