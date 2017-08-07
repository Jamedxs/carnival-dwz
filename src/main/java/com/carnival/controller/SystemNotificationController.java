
package com.carnival.controller;


import java.util.Map;
import com.carnival.entity.SystemNotification;
import com.carnival.service.SystemNotificationService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* ********************************************************
* @ClassName: SystemNotificationController
* @Description: 系统通知表
* @author 自动生成
* @date 2017-03-07 下午 03:30:18 
*******************************************************
*/
@Scope("prototype")
@Controller
@RequestMapping("/SystemNotification")
public class SystemNotificationController extends BaseController{

	@Resource
	private SystemNotificationService systemNotificationService;

	/**
	 * ********************************************************
	 * @Title: list
	 * @Description: 分页,条件查询
	 * @return String
	 * @date 2017-03-07 下午 03:30:18 
	 ********************************************************
	 */
	 @RequestMapping("/list")
	public String list(){
		systemNotificationService.getPageList(this.getPage());
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: add
	 * @Description: 添加、显示
	 * @return String
	 * @date 2017-03-07 下午 03:30:18 
	 ********************************************************
	 */
	 @RequestMapping("/add")
	public String add(@RequestParam(value="id",required=false) Integer id){
		if(id!=null){
			SystemNotification systemNotification = systemNotificationService.getOne(id) ;
			this.setAttribute("systemNotification",systemNotification);
		}
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: save
	 * @Description: 修改保存
	 * @return String
	 * @date 2017-03-07 下午 03:30:18 
	 ********************************************************
	 */
	 @RequestMapping("/save")
	public @ResponseBody Map save(@ModelAttribute("SystemNotification") SystemNotification  systemNotification){
		int result=systemNotification.getId()==null?systemNotificationService.insert(systemNotification):systemNotificationService.update(systemNotification);
		return success("保存["+systemNotification.getId()+"]成功","");
	}

	/**
	 * ********************************************************
	 * @Title: delete
	 * @Description: 删除
	 * @return String
	 * @date 2017-03-07 下午 03:30:18 
	 ********************************************************
	 */
	 @RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam Integer id){
		int result=systemNotificationService.delete(id);
		return success("删除成功","");
	}

}

