package com.carnival.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
@SuppressWarnings("all")
public class Utils {
	public static Class getService(String action){
		try {
			Class aclass = Class.forName("com.carnival.service."+action+"Service");
			return aclass;
		} catch (ClassNotFoundException e) {
			return null;
		}		
	}
	/**
	 * ********************************************************
	 * @Title: getResponse
	 * @Description: TODO(获取RESPONSE)
	 * @return HttpServletResponse
	 * @date 2014-11-1 下午05:29:39
	 ********************************************************
	 */
	public static HttpServletResponse getResponse(){
 	   return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
	}
	/**
	 * ********************************************************
	 * @Title: getRequest
	 * @Description: TODO(获取Request)
	 * @return HttpServletRequest
	 * @date 2014-11-1 下午05:29:54
	 ********************************************************
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * ********************************************************
	 * @Title: getSession
	 * @Description: 获取Session
	 * @return Session
	 * @date 2014-8-12 上午12:59:09
	 ********************************************************
	 */	
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
   /**
    * ********************************************************
    * @Title: showMes
    * @Description: TODO(无页面输出信息)
    * @param content
    * @return String
    * @date 2014-11-1 下午05:30:44
    ********************************************************
    */
	public static String showMes(String content){
		HttpServletResponse response=getResponse();
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	/**
	 * ********************************************************
	 * @Title: showAuto
	 * @Description: TODO(输出autocomplete 格式如：ss|ww|bb\n)
	 * @param listMap
	 * @return String
	 * @date 2014-11-1 下午05:31:37    ss|ww|bb\
	 ********************************************************
	 */
	public static String showAuto(List listMap){
		String allStr="";	
		StringBuffer buffer = new StringBuffer();
        List<LinkedHashMap<String,Object>> linkMap=(List<LinkedHashMap<String,Object>>)listMap;
		for(LinkedHashMap<String,Object> m:linkMap){
			String str="";
			for(Entry<String, Object> dataKey : m.entrySet()){
				str+=str==""?m.get(dataKey.toString()).toString():"|"+m.get(dataKey.toString()).toString();
			}
			buffer.append(str+"\n");
			allStr=buffer.toString();
		}
		return allStr;
	}
	

    /**
     * ********************************************************
     * @Title: getUtf8
     * @Description: TODO(获取UFT8的编码，解决URL传中文参数乱码)
     * @param str
     * @return String
     * @date 2014-11-1 下午05:31:59
     ********************************************************
     */
	public static String getUtf8(String str){
		try {
			return new String(str.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "";
	}
	
	/**-----------------------cookie -- start------------*/

	/**
	 * ********************************************************
	 * @Title: getCookieByName
	 * @Description: TODO(根据名字获取cookie)
	 * @param request
	 * @param name   cookie名字
	 * @return Cookie
	 * @date 2014-11-1 下午09:53:59
	 ********************************************************
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = readCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }  
	}
	 
	 
	 
   /**
    * ********************************************************
    * @Title: ReadCookieMap
    * @Description: TODO(将cookie封装到Map里面)
    * @param request
    * @return Map<String,Cookie>
    * @date 2014-11-1 下午09:51:20
    ********************************************************
    */
	public static Map<String,Cookie> readCookieMap(HttpServletRequest request){ 
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}

    /**
     * ********************************************************
     * @Title: addCookie
     * @Description: TODO(设置cookie)
     * @param response
     * @param name  cookie名字
     * @param value cookie值
     * @param maxAge cookie生命周期  以秒为单位
     * @date 2014-11-1 下午09:51:47
     ********************************************************
     */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
	    Cookie cookie = new Cookie(name,value);
	    cookie.setPath("/");
	    if(maxAge>0)  cookie.setMaxAge(maxAge);
	    response.addCookie(cookie);
	}	
	/**-----------------------cookie -- end------------*/

	/**
	 * ********************************************************
	 * @Title: isHasMethod
	 * @Description: TODO(某类中，是否存在某方法)
	 * @param className  类名称com.carnival........
	 * @param methodName 方法名称
	 * @return boolean
	 * @date 2014-11-1 下午09:52:59
	 ********************************************************
	 */
	public static boolean isHasMethod(String className,String methodName){
		Class cls;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			return false;	
		}
		Method[] mss=cls.getMethods();
		boolean method=false;//是否存在，默认不存在
		for(Method ms:mss){
			if(ms.getName().equals(methodName)){
				return true;
			}				
		}
		return false;
	}
    
	/**
	 * ********************************************************
	 * @Title: listToMap
	 * @Description: TODO(List转Map<String,Map>,某字段值做为Map的key)
	 * @param l    要转换的LIST
	 * @param key  作为键名的字段名
	 * @return Map<String,Map<String,Object>>
	 * @date 2014-11-1 下午11:40:41
	 ********************************************************
	 */
	public static Map<String,Map<String,Object>> listToMap(List<Map<String,Object>> l,String key){
		Map<String,Map<String,Object>> mm=new LinkedHashMap<String,Map<String,Object>>();	
		for(Map<String,Object> m:l){			
			if(m.get(key)!=null){
				mm.put(m.get(key).toString(), m);
			}
		}
		return mm;
	}
	
    /**
     * ********************************************************
     * @Title: listToMap
     * @Description: TODO(List转Map<String,String>，从List中挑出两字段组成Map<String,String>)
     * @param l
     * @param key
     * @param v
     * @return Map<String,String>
     * @date 2014-11-1 下午11:41:48
     ********************************************************
     */
	
	public static Map<String,String> listToMap(List<Map<String,Object>> l,String key,String v){
		Map<String,String> mm=new LinkedHashMap<String,String>();	
		for(Map<String,Object> m:l){
			if(m.get(key)!=null){				
				mm.put(m.get(key).toString(), m.get(v).toString());
			}
		}
		return mm;
	}

   /**
    * ********************************************************
    * @Title: toZtreeList
    * @Description: TODO(把普通 LIST变成ZTREE要求的list)
    * @param l
    * @param treeKey  treeKey "id","name","pId" 对应 list里哪些键值
    * @return List<Map<String,Object>>
    * @date 2014-11-1 下午11:42:07
    ********************************************************
    */
	public static List<Map<String,Object>> toZtreeList(List<Map<String,Object>> l,Map<String,String> treeKey){
		List<Map<String,Object>> nl=new ArrayList<Map<String,Object>>();		
		try{			
			for(Map<String,Object> m:l){
				Map<String,Object> current_m=new LinkedHashMap<String,Object>();
				current_m.put("id", m.get(treeKey.get("id")));
				current_m.put("name", m.get(treeKey.get("name")));
				current_m.put("pId", m.get(treeKey.get("pId")));
				nl.add(current_m);
				current_m=null;
			}		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return nl;
	}
	
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] saveErrorLog <br>
	 * [描述] 保存错误日志 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] boolean <br>
	 * [时间] 2015-4-14 下午03:26:45 <br>
	 *********************************************************.<br>
	 */
	public static boolean saveErrorLog(String msg) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			String path = System.getProperty("user.dir")+"/LogForPaidManager/";
			File file = new File(path);
			if(!file.exists()){
				boolean rst=file.mkdir();
				if(rst==false){
					return false;
				}
			}
			String filepath=path +formatdate(2)+".txt";
			fos = new FileOutputStream(filepath, true);
			osw = new OutputStreamWriter(fos, "gbk");
			bw = new BufferedWriter(osw);
			bw.write("时间:" + formatdate(1) + "\r\n内容:" + msg);
			bw.newLine();
			bw.newLine();
			
			bw.close();
			osw.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] formatdate <br>
	 * [描述] 日期格式化 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2015-4-14 下午03:26:32 <br>
	 *********************************************************.<br>
	 */
	public static String formatdate(int style) {
		String type = "yyyyMMdd";
		if (style == 1)
			type = "yyyy-MM-dd HH:mm:ss";
		if (style == 2)
			type = "yyyyMMdd";
		if (style == 3)
			type = "yyyyMMddhhmmss";

		SimpleDateFormat sdf = new SimpleDateFormat(type);
		String date = sdf.format(new Date());
		return date;
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getChargeNo <br>
	 * [描述] 收费单据号 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2015-5-13 上午10:08:58 <br>
	 *********************************************************.<br>
	 */
	public static String getChargeNo(){
		return "SF-"+System.currentTimeMillis()+"";
	}
}

	
