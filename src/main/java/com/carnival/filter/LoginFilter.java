package com.carnival.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.md.Encryption.EncryptionDES;
import com.carnival.entity.AdminInfo;
import com.carnival.util.MemCached;
import com.carnival.util.XssRequestWrapper;

/**
 * @author dengxusheng
 * @create 2017年3月6日 上午9:13:37
 */

public class LoginFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterchain)
			throws ServletException, IOException {
		//获取当前用户对象
		AdminInfo users = ((AdminInfo)request.getSession().getAttribute("admininfom"));
		String uri = request.getRequestURI();
		 //获取参数值
        String spm =request.getParameter("spm");
		//得到的结果为： /项目名
		String base = request.getContextPath();
		if(uri.endsWith(".xls")){
			filterchain.doFilter(new XssRequestWrapper(request), response);	
		}else{
		if ((users ==null || users.getAdmin_name() == null || "".equals(users.getAdmin_name())) && !uri.startsWith(base+"/public/")
			 && !uri.startsWith(base+"/AdminInfo/login")&& !uri.startsWith(base+"/AdminInfo/exitlogin")
			 && !uri.startsWith(base+"/AdminInfo/loginUsers") && !uri.startsWith(base+"/AdminInfo/nologin")&& !uri.startsWith(base+"/CaptchaImageCreate/captcha-image")
			 ) {         //杩樻病鐧诲綍 
			    response.sendRedirect(base+"/AdminInfo/nologin");
		}else{
			if(uri.endsWith(base)||uri.endsWith(base+"/")){
				if(spm==null||"".equals(spm)){
					response.sendRedirect(base+"/AdminInfo/login");
				}else{
					String key = (String)MemCached.getInstance().get("encryKey_"+users.getAdmin_name());
					try {
						//设置请求编码格式
						request.setCharacterEncoding("utf-8");
						//获取参数列表
						String urlParams = request.getQueryString();
						//解密跳转地址
						String url = "";
						//声明参数
						String params = "";
						//解密参数
						String [] spmArr = urlParams.split("\\&");
						//参数
						String pram = "";
						//解密url地址
						url = new EncryptionDES(key).decrypt(spmArr[0].split("=")[1]);
						//如果是注销操作
						if(url.equals("/AdminInfo/exitUsers")){
							request.getSession().invalidate();
							response.sendRedirect(base+"/AdminInfo/login");
						}else{
							for(int i = 1;i<spmArr.length;i++){
								String [] paramArr = spmArr[i].split("=");
								if(paramArr.length==2){
									String paramKey = paramArr[0];
									String param = paramArr[1];
									//解密参数  
									if(!paramKey.equals("_")&&!paramKey.equals("stringCartes")&&!paramKey.equals("pageNum")&&!paramKey.equals("totalRecord")
										&&!paramKey.equals("numPerPage")&&!paramKey.equals("orderField")&&!paramKey.equals("orderDirection")
										&&!paramKey.equals("carte_id")&&!paramKey.equals("_id")&&!paramKey.equals("_detailed_description")
										&&!paramKey.equals("_summary")&&!paramKey.equals("_type_name")&&!paramKey.equals("filedata")
										&&!paramKey.equals("buttonData")&&!paramKey.equals("buttonChecked")&&!paramKey.equals("key_en")
										&&!paramKey.equals("id")&&!paramKey.equals("admin_name")&&!paramKey.equals("_bankno")
										&&!paramKey.equals("channel_no")&&!paramKey.equals("enterpriseBaseNo")
										&&!paramKey.equals("account_num")&&!paramKey.equals("idss")
										&&!paramKey.equals("bno")&&!paramKey.equals("liqtime")
										&&!paramKey.equals("exp_date")&&!paramKey.equals("txtName")
										&&!paramKey.equals("note")&&!paramKey.equals("lists")
										){
										params = new EncryptionDES(key).decrypt(param);
										paramKey+="="+params;
										pram += pram.equals("")?"?"+paramKey:"&"+paramKey;
									}else if (paramKey.equals("key_en") || paramKey.equals("id")) {
										params = param;
										paramKey+="="+params;
										pram += pram.equals("")?"?"+paramKey:"&"+paramKey;
									}
								}
							}
							//重构真实路径
							pram = pram.equals("")?url:url+pram;
							
							//转发路径
							request.getRequestDispatcher(pram).forward(new XssRequestWrapper(request), response);
//							request.getRequestDispatcher(pram).forward(request, response);
						}
					} catch (Exception e) {
						e.printStackTrace();
						//自己改成想要跳转的页面
						response.sendRedirect(base+"/AdminInfo/pageNotFind");
					}
				}
			}else{
				if(uri.startsWith(base+"/Index/index") || uri.startsWith(base+"/public/") || uri.startsWith(base+"/AdminInfo/login") 
					|| uri.startsWith(base+"/AdminInfo/loginUsers")  || uri.startsWith(base+"/CaptchaImageCreate/captcha-image") || uri.endsWith(base+"/BackCarte/delete/")
			        || uri.startsWith(base+"/AdminInfo/pageNotFind") || uri.startsWith(base+"/AdminInfo/nologin") || uri.startsWith(base+"/AdminInfo/publicChangePwdForUser") || uri.startsWith(base+"/AdminInfo/changePwd") || uri.startsWith(base+"/AdminInfo/exitlogin")
			        || uri.endsWith("/Permission/getBtnIsShow")){
					filterchain.doFilter(new XssRequestWrapper(request), response);
//					filterchain.doFilter(request, response);
				}else{
					//自己改成想要跳转的页面
					response.sendRedirect(base+"/AdminInfo/pageNotFind");
				}
			}
		}
		}
	}
}

