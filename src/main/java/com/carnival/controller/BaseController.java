package com.carnival.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.md.Encryption.EncryptionDES;
import com.carnival.entity.AdminInfo;
import com.carnival.entity.Page;
import com.carnival.listener.BaseInfoListener;
import com.carnival.util.DataCache;
import com.carnival.util.GetCacheMethod;
import com.carnival.util.MemCached;

/**
 * @author dengxusheng
 * @create 2017年3月5日 下午9:31:07
 */
@Scope("prototype")
@SuppressWarnings("all")
public class BaseController{
	private Page page;	
	protected Map mes=new HashMap<String,String>();//消息
	protected String message = "";//记录操作结果信息
	protected int resForFinally = 0;//默认成功,前台返回成功或者失败
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected DataCache cache=new DataCache();
	
	
	static{
		BaseInfoListener bil=new BaseInfoListener();
		bil.BaseInfoCache();
	}
	
    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request,HttpServletResponse response){
    	this.request=request;
    	this.response=response;
    }

	/********************************************************
	 * @Title: getRequest
	 * @Description:返回当前调用方法的类名与方法名，做为VIEW的地址
	 * @return current class/method
	 * @throws Exception 
	 *********************************************************/
	public String display(){	    
		//String action = new Exception().getStackTrace()[1].getFileName().toString().replace("Controller.java", "");	//action名称
		String[] class_str=(this.getClass().getName()).split("\\.");	
		String action=class_str[class_str.length-1].replace("Controller", "");
		String method = new Exception().getStackTrace()[1].getMethodName().toString();	//方法名称
		String base = getRequest().getContextPath();          
	    String fullPath = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getLocalPort()+base;  
	    //zb
	    setAttribute("action","/"+action);   // /action
	    String urlEncrypt = "";
	    try {
	    	//加密key
	    	String key = (String) MemCached.getInstance().get("encryKey_"+getUserName());
	    	if (key!=null) {
	    		//加密url
		    	urlEncrypt = new EncryptionDES(key).encrypt("/"+action+"/"+method);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    setAttribute("base",fullPath);   //http://localhost/medicine
	    setAttribute("baseClass",fullPath+"/"+action);   //http://localhost/finance/action	
	    setAttribute("url", "/carnival/?spm="+urlEncrypt);     // http://localhost:8088/carnival/Index/countryPage
	    this.setAttribute("getCache", new GetCacheMethod(response)); //注册freemaker 自定义函数
		if(this.page!=null){			
			this.setAttribute("p", page);			
		}
	    return action + "/"+ method;		
	}
	
	/**
	 * ********************************************************
	 * @Title: setAttribute
	 * @Description: 获取setAttribute
	 * @return void
	 ********************************************************/
	public void setAttribute(String name , Object value){		
		this.getRequest().setAttribute(name,value);
	}
	/**
	 * ********************************************************
	 * @Title: getParameter
	 * @Description: TODO(根据参数名获取值)
	 * @param argName
	 * @return String
	 ********************************************************
	 */
    public String getParameter(String argName){
    	return request.getParameter(argName);
    }
	
	/**
	 * ********************************************************
	 * @Title: getRequest
	 * @Description: 获取request
	 * @return HttpServletRequest
	 ********************************************************
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	/**
	 * ********************************************************
	 * @Title: getSession
	 * @Description: 获取Session
	 * @return Session
	 ********************************************************
	 */
	
	public HttpSession getSession(){
		return getRequest().getSession();
	}
	
	/**
	 * ********************************************************
	 * @Title: getResponse
	 * @Description: TODO(获取response)
	 * @return HttpServletResponse
	 ********************************************************
	 */
	public HttpServletResponse getResponse(){
		ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();	
		return servletContainer.getResponse();
	}
	
	/**
	 * ********************************************************
	 * @Title: Sucess
	 * @Description: TODO(显示成功信息)
	 * @param message
	 * @return Map<String,String>
	 ********************************************************
	 */
	public Map<String,String> success(String message,String navTabId){	
		if(message.equals("")){
			message="操作成功";
		}
		mes.put("message", message);
		mes.put("statusCode", "200");
		mes.put("navTabId", navTabId);
		return mes;
	}
	/**
	 * ********************************************************
	 * @Title: message
	 * @Description: TODO(返回消息到前端)
	 * @param message
	 * @param result
	 * @return Map<String,String>
	 ********************************************************
	 */
	public Map<String,String> message(String message,boolean result){
		mes.put("statusCode", result?"200":"300");
		mes.put("message", message);
		return mes;
	}
	
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getUserName <br>
	 * [描述] 获取当前用户登录名 <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 *********************************************************.<br>
	 */
	public static String getUserName(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Object obj=request.getSession().getAttribute("admininfom");
		String userName ="";
		if (obj!=null) {
			userName = ((AdminInfo)obj).getAdmin_name();
		}
		return userName;
	}
	
	/**
	 * ********************************************************
	 * @Title: message
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @param message
	 * @param result
	 * @return Map<String,String>
	 ********************************************************
	 */
	public Map<String,String> message(String message,int result){
		mes.put("statusCode", result==0?"200":"300");
		mes.put("message", message);
		return mes;
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getCurrentTime <br>
	 * [描述] TODO(这里用一句话描述这个方法的作用) <br>
	 * [参数] type 0 yyyy-MM-dd HH:mm:ss 1 yyyy-MM-dd hh:mm:ss 2 yyyy-MM-dd <br>
	 * [返回] String <br>
	 *********************************************************.<br>
	 */
	public String getCurrentTime(int type){
		String dateType = "";
		switch (type) {
			case 0:
				dateType = "yyyy-MM-dd HH:mm:ss";
				break;
			case 1:
				dateType = "yyyy-MM-dd hh:mm:ss";
				break;
			case 2:
				dateType = "yyyy-MM-dd";
				break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateType);
		String dateTime = sdf.format(new Date());
		return dateTime;
	}
	
	//---------------------------------------
	public Page getPage() {	
		if(page==null){			
			page=new Page();			
		}		
		return page;
	}

	public void setPage(Page page) {	
		this.page = page;
	}
	public Map getMes() {
		return mes;
	}

	public void setMes(Map mes) {
		this.mes = mes;
	}
	
	//这里session会丢失
	public void setAdminInfo(AdminInfo admininfo){
		request.getSession().setAttribute("admininfom", admininfo);
	}
	public AdminInfo getAdminInfo(){
		AdminInfo admin=(AdminInfo)request.getSession().getAttribute("admininfom");
		return admin;
	}

	
	
}

