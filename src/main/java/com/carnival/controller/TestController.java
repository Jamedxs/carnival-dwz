package com.carnival.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dengxusheng
 * @create 2017年3月6日 下午1:49:36
 */
@Controller
@RequestMapping("/testController")
public class TestController {
	public TestController() {}
	
	/**
	  * 测试
	  * @return
	  */
	 @RequestMapping(value="/test")
	 public String test(){
		 String str = "Test the Controller!";  
		 System.out.println(str);
		 return null;
	 }
	
}
