
package com.carnival.controller;


import java.util.Map;
import com.carnival.entity.Permission;
import com.carnival.service.PermissionService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* ********************************************************
* @ClassName: PermissionController
* @Description: 按钮表
* @author 自动生成
* @date 2017-03-07 下午 03:29:56 
*******************************************************
*/
@Scope("prototype")
@Controller
@RequestMapping("/Permission")
public class PermissionController extends BaseController{

	@Resource
	private PermissionService permissionService;

	/**
	 * ********************************************************
	 * @Title: list
	 * @Description: 分页,条件查询
	 * @return String
	 * @date 2017-03-07 下午 03:29:56 
	 ********************************************************
	 */
	 @RequestMapping("/list")
	public String list(){
		permissionService.getPageList(this.getPage());
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: add
	 * @Description: 添加、显示
	 * @return String
	 * @date 2017-03-07 下午 03:29:56 
	 ********************************************************
	 */
	 @RequestMapping("/add")
	public String add(@RequestParam(value="id",required=false) Integer id){
		if(id!=null){
			Permission permission = permissionService.getOne(id) ;
			this.setAttribute("permission",permission);
		}
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: save
	 * @Description: 修改保存
	 * @return String
	 * @date 2017-03-07 下午 03:29:56 
	 ********************************************************
	 */
	 @RequestMapping("/save")
	public @ResponseBody Map save(@ModelAttribute("Permission") Permission  permission){
		int result=permission.getId()==null?permissionService.insert(permission):permissionService.update(permission);
		return success("保存["+permission.getId()+"]成功","");
	}

	/**
	 * ********************************************************
	 * @Title: delete
	 * @Description: 删除
	 * @return String
	 * @date 2017-03-07 下午 03:29:56 
	 ********************************************************
	 */
	 @RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam Integer id){
		int result=permissionService.delete(id);
		return success("删除成功","");
	}

}

