
package com.carnival.controller;


import java.util.Map;
import com.carnival.entity.BaseinfoValue;
import com.carnival.service.BaseinfoValueService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* ********************************************************
* @ClassName: BaseinfoValueController
* @Description: 基础数据VALUE表
* @author 自动生成
* @date 2017-03-07 下午 03:29:19 
*******************************************************
*/
@Scope("prototype")
@Controller
@RequestMapping("/BaseinfoValue")
public class BaseinfoValueController extends BaseController{

	@Resource
	private BaseinfoValueService baseinfoValueService;

	/**
	 * ********************************************************
	 * @Title: list
	 * @Description: 分页,条件查询
	 * @return String
	 * @date 2017-03-07 下午 03:29:19 
	 ********************************************************
	 */
	 @RequestMapping("/list")
	public String list(){
		baseinfoValueService.getPageList(this.getPage());
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: add
	 * @Description: 添加、显示
	 * @return String
	 * @date 2017-03-07 下午 03:29:19 
	 ********************************************************
	 */
	 @RequestMapping("/add")
	public String add(@RequestParam(value="id",required=false) Integer id){
		if(id!=null){
			BaseinfoValue baseinfoValue = baseinfoValueService.getOne(id) ;
			this.setAttribute("baseinfoValue",baseinfoValue);
		}
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: save
	 * @Description: 修改保存
	 * @return String
	 * @date 2017-03-07 下午 03:29:19 
	 ********************************************************
	 */
	 @RequestMapping("/save")
	public @ResponseBody Map save(@ModelAttribute("BaseinfoValue") BaseinfoValue  baseinfoValue){
		int result=baseinfoValue.getId()==null?baseinfoValueService.insert(baseinfoValue):baseinfoValueService.update(baseinfoValue);
		return success("保存["+baseinfoValue.getId()+"]成功","");
	}

	/**
	 * ********************************************************
	 * @Title: delete
	 * @Description: 删除
	 * @return String
	 * @date 2017-03-07 下午 03:29:19 
	 ********************************************************
	 */
	 @RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam Integer id){
		int result=baseinfoValueService.delete(id);
		return success("删除成功","");
	}

}

