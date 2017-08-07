
package com.carnival.controller;


import java.util.Map;
import com.carnival.entity.PermissionMapping;
import com.carnival.service.PermissionMappingService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* ********************************************************
* @ClassName: PermissionMappingController
* @Description: 按钮分配
* @author 自动生成
* @date 2017-03-07 下午 03:30:06 
*******************************************************
*/
@Scope("prototype")
@Controller
@RequestMapping("/PermissionMapping")
public class PermissionMappingController extends BaseController{

	@Resource
	private PermissionMappingService permissionMappingService;

	/**
	 * ********************************************************
	 * @Title: list
	 * @Description: 分页,条件查询
	 * @return String
	 * @date 2017-03-07 下午 03:30:06 
	 ********************************************************
	 */
	 @RequestMapping("/list")
	public String list(){
		permissionMappingService.getPageList(this.getPage());
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: add
	 * @Description: 添加、显示
	 * @return String
	 * @date 2017-03-07 下午 03:30:06 
	 ********************************************************
	 */
	 @RequestMapping("/add")
	public String add(@RequestParam(value="id",required=false) Integer id){
		if(id!=null){
			PermissionMapping permissionMapping = permissionMappingService.getOne(id) ;
			this.setAttribute("permissionMapping",permissionMapping);
		}
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: save
	 * @Description: 修改保存
	 * @return String
	 * @date 2017-03-07 下午 03:30:06 
	 ********************************************************
	 */
	 @RequestMapping("/save")
	public @ResponseBody Map save(@ModelAttribute("PermissionMapping") PermissionMapping  permissionMapping){
		int result=permissionMapping.getId()==null?permissionMappingService.insert(permissionMapping):permissionMappingService.update(permissionMapping);
		return success("保存["+permissionMapping.getId()+"]成功","");
	}

	/**
	 * ********************************************************
	 * @Title: delete
	 * @Description: 删除
	 * @return String
	 * @date 2017-03-07 下午 03:30:06 
	 ********************************************************
	 */
	 @RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam Integer id){
		int result=permissionMappingService.delete(id);
		return success("删除成功","");
	}

}

