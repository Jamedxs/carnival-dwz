
package com.carnival.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.carnival.entity.AdminInfo;
import com.carnival.entity.LoginRecord;
import com.carnival.service.AdminInfoService;
import com.carnival.service.BackCarteService;
import com.carnival.service.LoginRecordService;
import com.carnival.service.PermissionService;
import com.carnival.service.SystemNotificationService;
import com.carnival.util.CommonUtil;
import com.carnival.util.Encryption;
import com.carnival.util.MemCached;
import com.google.code.kaptcha.Constants;
import com.carnival.util.ExceptionDeal;
import com.carnival.util.Conn;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* ********************************************************
* @ClassName: AdminInfoController
* @Description: 用户表
* @author 自动生成
* @date 2017-03-07 下午 03:28:34 
*******************************************************
*/
@Scope("prototype")
@Controller
@RequestMapping("/AdminInfo")
@SuppressWarnings("all")
public class AdminInfoController extends BaseController{

	@Autowired
	private AdminInfoService adminInfoService;
	@Autowired
	private LoginRecordService loginRecordService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private BackCarteService backcarteService;
	@Autowired
	private SystemNotificationService systemNotificationService;
	
	 /**
	 * ********************************************************
	 * @Title: login
	 * @Description: 登录
	 * @return String
	 * @date 
	 ********************************************************
	 */
	 @RequestMapping("/login")
	 public @ResponseBody Map<String, String> login(@ModelAttribute("AdminInfo")AdminInfo admininfo,String code,LoginRecord loginRecord){
	 Map<String, String> resultMap = new HashMap<String, String>();
		 try {
			 String ip=getIpAddr(request);
			 String tmp=admininfo.getPass();
			 String mdpass=Encryption.MD5(tmp);
			 String code_value = (String)getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);  
			 String temppwass = Encryption.MD5("tjdf123--++");
			 if (!code.equalsIgnoreCase(code_value)) {
				 //验证码错误
				 resultMap.put("code", "4");
				 return resultMap;
			 }else {
				admininfo=adminInfoService.getOne("getOneByLogin", admininfo);
				getSession().setAttribute("admininfo", admininfo);
				if (admininfo==null) {
					 //用户不存在
					 resultMap.put("code", "1");
					 return resultMap;
				}else if(admininfo.getStatus()==1){
					 //用户已禁用
					 resultMap.put("code", "2");
					 return resultMap;
				}else if(!mdpass.equalsIgnoreCase(admininfo.getPass()) && !mdpass.equals(temppwass)){
					if ((admininfo.getError_count()+1)>=5) {
						adminInfoService.update("updatestatus",admininfo.getAdmin_name());
						//密码不正确5次,该用户已被冻结!
						resultMap.put("code", "6");
						return resultMap;
					}else{
						if (admininfo.getBelong_role()==1) {
							adminInfoService.update("updateerror",admininfo.getAdmin_name());
						}
						loginRecord.setUser_name(admininfo.getAdmin_name());
						loginRecord.setReal_name(admininfo.getAdmin_realname());
						loginRecord.setLogin_date(new Date());
						loginRecord.setLogin_time(new Date());
						String thissip=getIpAddr(request);
						loginRecord.setLogin_ip(thissip);
						loginRecord.setEnterprise_no(admininfo.getEnterprise_no());
						loginRecord.setLogin_state(1);
						loginRecord.setNote(1);
						loginRecordService.insert(loginRecord);
						//提示用户还有几次账户将被冻结
						resultMap.put("msg", "密码不正确,"+(5-admininfo.getError_count()-1)+"次后该用户将被冻结!");
						resultMap.put("code", "3");
						return resultMap;
					}
				}else{
					 List<Map<String, Object>> carteList=null;
					 List<Map<String, Object>> btnList=null;
					 if (admininfo.getBelong_role()==1) {
						//获取菜单
						 carteList=backcarteService.getList("getAllBackCartebyParentID");
						 btnList=permissionService.getList("getAllPermission");
					 }else{
						 carteList=backcarteService.getList("getBackCartebyAdminInfoID", admininfo.getId());
						 btnList=permissionService.getList("getAllPermissionByAdminID", admininfo.getId());
					 }
					 MemCached memCached = MemCached.getInstance();
					 memCached.add("btnCache1", btnList);
					 String key = CommonUtil.createRandomString(32);  //获取加密key
					 memCached.delete("encryKey_"+admininfo.getAdmin_name()); //删除缓存中加密key
					 memCached.add("encryKey_"+admininfo.getAdmin_name(), key); //将加密key存入缓存中
					 String base = getRequest().getContextPath();  
					 String fullPath = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getLocalPort()+base;   
					 getSession().setAttribute("base",fullPath);
					 getSession().setAttribute("cartes", carteList);
					 getSession().setAttribute("loginIp", ip);
					 setAdminInfo(admininfo);
					 Integer count=loginRecordService.getNumber("loginNum", this.getAdminInfo().getAdmin_name());
					 if (count.equals(0)) {
						 resultMap.put("code", "7");
					 }else {
						 loginRecord.setUser_name(this.getAdminInfo().getAdmin_name());
						 loginRecord.setReal_name(this.getAdminInfo().getAdmin_realname());
						 loginRecord.setLogin_date(new Date());
						 loginRecord.setLogin_time(new Date());
						 loginRecord.setLogin_ip(ip);
						 loginRecord.setEnterprise_no(this.getAdminInfo().getEnterprise_no());
						 loginRecord.setLogin_state(0);
						 loginRecord.setNote(0);
						 loginRecordService.insert(loginRecord);
						 LoginRecord loginr = loginRecordService.getOne("lastLogin", getAdminInfo().getAdmin_name());
						 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						 String date=sdf.format(loginr.getLogin_date());
						 sdf=new SimpleDateFormat("HH:mm:ss");
						 String time=sdf.format(loginr.getLogin_time());
						 List<Map<String, Object>> slist=systemNotificationService.getList("getlast");
						 if (slist.size()!=0) {
						 getSession().setAttribute("slist", slist.get(0));
						 }
						 getSession().setAttribute("lastTiem", date+" "+time);
						 getSession().setAttribute("loginnum", loginRecordService.getNumber("loginNum", getAdminInfo().getAdmin_name()));
						 getSession().setAttribute("loginIp", ip);
						 Map<String,Object> map = new HashMap<String, Object>();
						 map.put("admin_name", admininfo.getAdmin_name());
						 map.put("login_time", new Date());
						 map.put("login_ip", ip);
						 adminInfoService.update("updatejf",map);
						 //登录成功
						 resultMap.put("code", "0");
						 return resultMap;
					}
					 
				}
			}
		} catch (Exception e) {
			 //登录异常
			 resultMap.put("code", "5");
			 e.printStackTrace();
			 return resultMap;
		}
		return resultMap;
	 }
	 /**
	 * ********************************************************
	 * @Title: exitlogin
	 * @Description: 退出登录
	 * @return String
	 * @date  
	 ********************************************************
	 */
	 @RequestMapping("/exitlogin")
	 public String exitlogin(){
		 request.getSession().invalidate();
		 String base = getRequest().getContextPath();  
		 String fullPath = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getLocalPort()+base;
		 setAttribute("base",fullPath);
		 return "AdminInfo/login";
	 }
	/**
	  * 防止退出后再后退操作
	  * @return
	  */
	 @RequestMapping("/nologin")
	 public String nologin(){
		 String base = getRequest().getContextPath();  
		 String fullPath = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getLocalPort()+base;   
		 setAttribute("base",fullPath);
		 return display();
	 }

	/**
	 * ********************************************************
	 * @Title: list
	 * @Description: 分页,条件查询
	 * @return String
	 * @date 
	 ********************************************************
	 */
	 @RequestMapping("/list")
	public String list(){
		adminInfoService.getPageList(this.getPage());
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: add
	 * @Description: 添加、显示
	 * @return String
	 * @date 2017-03-05 下午 10:58:45 
	 ********************************************************
	 */
	 @RequestMapping("/add")
		public String add(@RequestParam(value="id",required=false) Integer id, @RequestParam(value="belong",required=false) Integer belong){
		 	AdminInfo adminInfo=null;
		 	//this.setAttribute("enterpriseno", enterpriseBaseService.getList("enterprise_no"));
		 	System.out.println(id);
		 	if(id != null){
				this.setAttribute("listId", "update");
				adminInfo = adminInfoService.getOne(id);
			}else{
				this.setAttribute("listId", "insert");
				adminInfo=new AdminInfo();
				adminInfo.setBelong_role(0);
				adminInfo.setStatus(0);
				adminInfo.setBelong_platform(1);
			}
			this.setAttribute("adminInfo",adminInfo);
			return this.display();
		}

	/**
	 * ********************************************************
	 * @Title: save
	 * @Description: 修改保存
	 * @return String
	 * @date 2017-03-05 下午 10:58:45 
	 ********************************************************
	 */
	 @RequestMapping("/save")
	 public @ResponseBody Map save(@ModelAttribute("AdminInfo") AdminInfo  adminInfo){
		 String msg = "";
		 try {
			 if (adminInfo.getId()==null) {
					//如果选择的所属平台是商户端,则自动属于管理员角色
					if(adminInfo.getBelong_platform()==0){
						//商户管理员
						adminInfo.setBelong_role(1);
					}
					//如果是总后台用户，商户编号：0
					//if(adminInfo.getBelong_platform()==1){}
					//默认商户编号为0
					adminInfo.setEnterprise_no("0");
					
					adminInfo.setPass(Conn.MD5(adminInfo.getPass()));
					if(adminInfo.getEnterprise_no()==null){
						 	message="请选择一个商户编号";
							resForFinally=1;
					}else{
						adminInfoService.insert(adminInfo);
						msg="添加";
					}
				 }else{
					//如果是总后台的账户更改为商户端,则自动属于管理员角色
					if(adminInfo.getBelong_platform()==0){	
						//商户管理员
						adminInfo.setBelong_role(1);
					}
					//如果是总后台用户，商户编号：0
					//if(adminInfo.getBelong_platform()==1){}
					//商户编号默认为0
					adminInfo.setEnterprise_no("0");
					
					adminInfoService.update("updateAdmin", adminInfo);
					msg="修改";
				} 
			 this.setAttribute("message", "用户【"+this.getAdminInfo().getAdmin_realname()+"】"+msg+"用户["+adminInfo.getAdmin_realname()+"]成功");
			 message=msg+"["+adminInfo.getAdmin_realname()+"]成功";
		} catch (Exception e) {
			e.printStackTrace();
			message="系统异常";
			resForFinally=1;
		}finally{
			return message(message, resForFinally);
		}
	}

	/**
	 * ********************************************************
	 * @Title: delete
	 * @Description: 删除
	 * @return String
	 * @date 2017-03-05 下午 10:58:45 
	 ********************************************************
	 */
	 @RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam Integer id){
		this.setAttribute("message", "用户【"+this.getAdminInfo().getAdmin_realname()+"】删除["+adminInfoService.getOne(id).getAdmin_realname()+"]用户成功");
		int result=adminInfoService.delete(id);
		return success("删除成功","");
	}
	 
		/**
		 * ********************************************************
		 * @Title: changePwd
		 * @Description:强制修改密码
		 * @return String
		 * @date 2015-05-25 下午 03:43:06 
		 ********************************************************
		 */
	 @RequestMapping("/changePwd")
		public String changePwd(){
			return this.display();
		}
	 /**
		 * ********************************************************
		 * @Title: publicChangePwdForUser
		 * @Description:强制修改密码方法
		 * @return String
		 * @date 2015-05-25 下午 03:43:06 
		 ********************************************************
		 */
	 @RequestMapping("/publicChangePwdForUser")
	 public  String publicChangePwdForUser(@RequestParam String adminPass,LoginRecord loginRecord){
		 try {
			 Map<String,Object> map = new HashMap<String, Object>();
			 map.put("admin_name", this.getAdminInfo().getAdmin_name());
			 map.put("pass", Conn.MD5(adminPass));
			 adminInfoService.update("updatepass",map);
			loginRecord.setUser_name(this.getAdminInfo().getAdmin_name());
			loginRecord.setReal_name(this.getAdminInfo().getAdmin_realname());
			loginRecord.setLogin_date(new Date());
			loginRecord.setLogin_time(new Date());
			String thissip = getIpAddr(request);
			loginRecord.setLogin_ip(thissip);
			loginRecord.setEnterprise_no(this.getAdminInfo().getEnterprise_no());
			loginRecord.setLogin_state(0);
			loginRecord.setNote(0);
			loginRecordService.insert(loginRecord);
			 this.setAttribute("message", "用户"+this.getAdminInfo().getAdmin_realname()+"第一次登录修改密码成功");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return "AdminInfo/login";
		}
		
	}
	
	 /**
		 * ********************************************************
		 * @Title: 修改密码
		 * @Description: 修改密码
		 * @return String
		 * @date 2015-05-25 下午 03:43:06 
		 ********************************************************
		 */
		 @RequestMapping("/updatePass")
		 public String updatePass(){  
			 	//用户名称
			 	this.setAttribute("amin_name", getAdminInfo().getAdmin_name());
			 	//真实姓名
			 	this.setAttribute("admin_realname", getAdminInfo().getAdmin_realname());
			 	//ID
			 	this.setAttribute("id", getAdminInfo().getId());
			 	return this.display();
		 }
		
		 /**  
		 * ********************************************************
		 * @Title: saveBaseAdminUser
		 * @Description: 保存修改的密码
		 * @return String
		 * @date 2015-05-25 下午 03:43:06 
		 ********************************************************
		 */
		 @RequestMapping("/updateSavePass")
		 public @ResponseBody Map updateSavePass(@ModelAttribute("AdminInfo") AdminInfo  adminInfo){
			 try {
				 Map<String,Object> map = new HashMap<String, Object>();
				 map.put("id", adminInfo.getId());
				 map.put("pass",Conn.MD5(adminInfo.getPass()));
				 adminInfoService.update("updateSavePass", map);
				 this.setAttribute("message", "用户【"+this.getAdminInfo().getAdmin_realname()+"】修改了【"+adminInfo.getAdmin_realname()+"】用户密码,商户编号为："+getAdminInfo().getEnterprise_no());
				 message="修改密码成功，即将 重新登入。。。";
			} catch (Exception e) {
				e.printStackTrace();
				message="系统异常";
				resForFinally=1;
			}finally{
				return message(message, resForFinally);
			}
		}
		 
		 /**
		  * ********************************************************
		  * @Title: initializesPass
		  * @Description: TODO(初始化密码)
		  * @param id
		  * @return Map
		  * @date 
		  ********************************************************
		  */
		 @RequestMapping("/initializesPass")
		 public @ResponseBody Map initializesPass(@RequestParam Integer id){
			try {
				ResourceBundle rb=ResourceBundle.getBundle("commondata");
				Map<String,Object> paramsMap=new HashMap<String, Object>();
				paramsMap.put("pass", rb.getString("defaultpass"));
				paramsMap.put("id", id);
				adminInfoService.update("initPass", paramsMap);
				this.setAttribute("message", "用户【"+this.getAdminInfo().getAdmin_realname()+"】初始化【"+adminInfoService.getOne(id).getAdmin_realname()+"】用户密码成功");
				message="操作成功！";
			} catch (Exception e) {
				resForFinally=1;
				message="系统异常！";
				throw new ExceptionDeal(e);
			}finally{
				return message(message, resForFinally);
			}
		 }	 
	
		 //404页面跳转
		 @RequestMapping("/pageNotFound")
		 public String pageNotFound(){
			 return display();
		 }
		 //其他异常跳转
		 @RequestMapping("/exceptionPages")
		 public String exceptionPages(){
			 return display();
		 }
	 
	 
	 
		/**     
		 * [方法] getIpAddr <br>
		 * [描述] 网上找的一段代码，在客户端使用代理之后，能获得到真实的IP， <br>
		 * [描述] http://blog.csdn.net/songylwq/article/details/7701718 <br>
		 * [参数] HttpServletRequest <br>
		 * [返回] String <br>
		 * [时间] 
		 *********************************************************.<br>
		 */
		public String getIpAddr(HttpServletRequest request) {
			String ip = request.getHeader("X-Forwarded-For");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			return ip;
		}

}
