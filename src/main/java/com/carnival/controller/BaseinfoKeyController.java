
package com.carnival.controller;


import java.util.Map;
import com.carnival.entity.BaseinfoKey;
import com.carnival.service.BaseinfoKeyService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* ********************************************************
* @ClassName: BaseinfoKeyController
* @Description: 基础数据(KEY)
* @author 自动生成
* @date 2017-03-07 下午 03:29:08 
*******************************************************
*/
@Scope("prototype")
@Controller
@RequestMapping("/BaseinfoKey")
public class BaseinfoKeyController extends BaseController{

	@Resource
	private BaseinfoKeyService baseinfoKeyService;

	/**
	 * ********************************************************
	 * @Title: list
	 * @Description: 分页,条件查询
	 * @return String
	 * @date 2017-03-07 下午 03:29:08 
	 ********************************************************
	 */
	 @RequestMapping("/list")
	public String list(){
		baseinfoKeyService.getPageList(this.getPage());
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: add
	 * @Description: 添加、显示
	 * @return String
	 * @date 2017-03-07 下午 03:29:08 
	 ********************************************************
	 */
	 @RequestMapping("/add")
	public String add(@RequestParam(value="id",required=false) Integer id){
		if(id!=null){
			BaseinfoKey baseinfoKey = baseinfoKeyService.getOne(id) ;
			this.setAttribute("baseinfoKey",baseinfoKey);
		}
		return this.display();
	}

	/**
	 * ********************************************************
	 * @Title: save
	 * @Description: 修改保存
	 * @return String
	 * @date 2017-03-07 下午 03:29:08 
	 ********************************************************
	 */
	 @RequestMapping("/save")
	public @ResponseBody Map save(@ModelAttribute("BaseinfoKey") BaseinfoKey  baseinfoKey){
		int result=baseinfoKey.getId()==null?baseinfoKeyService.insert(baseinfoKey):baseinfoKeyService.update(baseinfoKey);
		return success("保存["+baseinfoKey.getId()+"]成功","");
	}

	/**
	 * ********************************************************
	 * @Title: delete
	 * @Description: 删除
	 * @return String
	 * @date 2017-03-07 下午 03:29:08 
	 ********************************************************
	 */
	 @RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam Integer id){
		int result=baseinfoKeyService.delete(id);
		return success("删除成功","");
	}

}

