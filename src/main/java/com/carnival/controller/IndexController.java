package com.carnival.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carnival.entity.AdminInfo;
import com.carnival.service.AdminInfoService;
import com.carnival.controller.BaseController;

/**
 * @author dengxusheng
 * @create 2017年3月7日 上午11:46:33
 */
@Scope("prototype")//保持单例模式
@Controller
@RequestMapping("/Index") 
@SuppressWarnings("all")
public class IndexController extends BaseController{

	@Resource
	private AdminInfoService admininfoService;

	/**
	 * ********************************************************
	 * @Title: index
	 * @Description: TODO(这里用一句话描述这个类的作用)--登录
	 * @return String
	 * @date 2014-8-12 下午10:53:58
	 ********************************************************
	 */
	@RequestMapping("/index") 
	public String index(@ModelAttribute("admininfo")AdminInfo admininfo,@RequestParam(value="count",required=false)String count){
		if (count==null) {
			this.setAttribute("count", 1);
		}else {
			this.setAttribute("count", 0);
		}
		return this.display();
		
	}
	
	/*@RequestMapping("/save")		
	public @ResponseBody Map save(@ModelAttribute("Country") Country c){
			
		return Sucess("添加成功");		success	
	}*/
	/**
	 * ********************************************************
	 * @Title: add
	 * @Description: TODO(这里用一句话描述这个类的作用) void
	 * @date 
	 ********************************************************
	 */
	@RequestMapping("/add")
	public String add(){
		return this.display();
	}
	
}
