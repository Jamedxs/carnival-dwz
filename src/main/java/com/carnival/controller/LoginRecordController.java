
package com.carnival.controller;


import java.util.Map;
import com.carnival.entity.LoginRecord;
import com.carnival.service.LoginRecordService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* ********************************************************
* @ClassName: LoginRecordController
* @Description: 登录记录表
* @author 自动生成
* @date 2017-03-07 下午 03:29:34 
*******************************************************
*/
@Scope("prototype")
@Controller
@RequestMapping("/LoginRecord")
public class LoginRecordController extends BaseController{

	@Resource
	private LoginRecordService loginRecordService;

	/**
	 * ********************************************************
	 * @Title: list
	 * @Description: 分页,条件查询
	 * @return String
	 * @date 2017-03-07 下午 03:29:34 
	 ********************************************************
	 */
	 @RequestMapping("/list")
	public String list(){
		loginRecordService.getPageList(this.getPage());
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: add
	 * @Description: 添加、显示
	 * @return String
	 * @date 2017-03-07 下午 03:29:34 
	 ********************************************************
	 */
	 @RequestMapping("/add")
	public String add(@RequestParam(value="id",required=false) Integer id){
		if(id!=null){
			LoginRecord loginRecord = loginRecordService.getOne(id) ;
			this.setAttribute("loginRecord",loginRecord);
		}
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: save
	 * @Description: 修改保存
	 * @return String
	 * @date 2017-03-07 下午 03:29:34 
	 ********************************************************
	 */
	 @RequestMapping("/save")
	public @ResponseBody Map save(@ModelAttribute("LoginRecord") LoginRecord  loginRecord){
		int result=loginRecord.getId()==null?loginRecordService.insert(loginRecord):loginRecordService.update(loginRecord);
		return success("保存["+loginRecord.getId()+"]成功","");
	}

	/**
	 * ********************************************************
	 * @Title: delete
	 * @Description: 删除
	 * @return String
	 * @date 2017-03-07 下午 03:29:34 
	 ********************************************************
	 */
	 @RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam Integer id){
		int result=loginRecordService.delete(id);
		return success("删除成功","");
	}

}

