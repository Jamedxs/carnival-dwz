
package common.newEntity.dispose;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.carnival.util.ExceptionDeal;


/**
 * ********************************************************
 * @ClassName: WriteEntity
 * @Description: 创建 hibernate 实体对象类
 * @author DoDo
 * @date 2012-12-26 下午06:02:04
 *******************************************************
 */
@SuppressWarnings("all")
public class WriteEntity {
	
	public String webHtml = "src\\main\\webapp\\views\\";   //页面生成路径(WEB 里边)
	
	private String pak = "com.carnival";             //包路径
	
	private String table_name = "";        //表名
	private String entity_name = "";       //类名
	private String entity_name_short = "";   //类名简称
	private String locahost = "";        //java代码生成路径
	
	public String address = "";             //项目绝对路径

	
	private String comments_table = "";     //表说明
	private Map<String,String> comment_map = new HashMap<String, String>();     //表属性明说
	private Map<String, String[][]> hibernate_map = new HashMap<String, String[][]>();          //hibernate 实体对象信息
	Map<String,String[][]> table_map = new HashMap<String, String[][]>();     //表信息
	
	/**
	 * ********************************************************
	 * @Title: Write
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @param table
	 * @return String
	 * @throws ExceptionDeal 
	 * @date 2012-12-26 下午06:02:27
	 ********************************************************
	 */
	public void write(String table) throws ExceptionDeal{
	
		
		table_name = table;
		
		ReadTable rt = new ReadTable();
		
		table_map = rt.read(table);     //表信息
		comments_table = rt.findTableComments(table);     //表说明
		comment_map =rt.findColComment(table);     //表属性明说
		hibernate_map = getHibernate(table_map);          //hibernate 实体对象信息
		
		
		locahost = System.getProperty("user.dir")+"\\src\\main\\java\\";
		
		entity_name = getEntityName();       //类名
		entity_name_short = getNameShort();    //类名简称
		
		
		//////////////////////////////
		add_sqlmapper();
		sqlmapper();         //
		entity();           //生成  entity
		dao();
		daoImpl();
		service();
		serviceImpl();
		action();
		list_html();
		find_html();
	}
	
	
	/**
	 * ********************************************************
	 * @Title: getHibernate
	 * @Description: 获取 hibernate 属性
	 * @param map    table 数据
	 * @return Map
	 * @date 2012-12-26 下午06:03:29
	 ********************************************************
	 */
	public Map getHibernate(Map<String,String[][]> map){
 
		Map<String ,String[][]> h_map = new HashMap<String, String[][]>();
		
		for (Map.Entry<String, String[][]> t_m : map.entrySet()) {     //hibernate 属性名 和 数据类型
			
			String h_name =  t_m.getKey()+"";
//			String h_name =  "";
//			String [] t_name = t_m.getKey().split("_");
//			for (int i = 0; i < t_name.length; i++) {
//				if (i > 0) {
//					h_name += t_name[i].substring(0,1).toUpperCase() + t_name[i].substring(1,t_name[i].length());
//				}else{
//					h_name += t_name[i].substring(0,t_name[i].length());
//				}
//			}
			
			String type = t_m.getValue()[0][0];
			if (type.equals("varchar2")||type.equals("varchar")||type.equals("nvarchar2")||type.equals("nvarchar")||type.equals("char")) {
				type = "String";
			}else if(type.equals("number")){

				if(Integer.parseInt(t_m.getValue()[0][1]) > 0){
					type = "Double";
				}else{
					type = "Integer";
				}
				
			}else if(type.equals("int")||type.equals("integer")){
				type = "Integer";
			}else if(type.equals("long")){
				type = "Long";
			}else if(type.equals("date")){
				type = "Date";
			}else if(type.equals("float")||type.equals("double")){
				type = "Double";
			}else if(type.equals("time")){
				type = "Date";
			}
			
			h_map.put(t_m.getKey() ,new String[][]{{h_name,type}} );      

		}
		
		return h_map;
	}
	
	/**
	 * ********************************************************
	 * @Title: getEntityName
	 * @Description: 生成对象名
	 * @return String
	 * @date 2012-12-27 下午03:11:19
	 ********************************************************
	 */
	public String getEntityName(){
		
		String [] t_name = table_name.toLowerCase().split("_");
		String e_name =  "";
		StringBuffer buffer=new StringBuffer();
		for (int i = 0; i < t_name.length; i++) {
			buffer.append(t_name[i].substring(0,1).toUpperCase() + t_name[i].substring(1,t_name[i].length()));
			e_name =buffer.toString() ;
		}
		
		return e_name;
	}
	
	/**
	 * ********************************************************
	 * @Title: getNameShort
	 * @Description: 类名简称
	 * @return String
	 * @date 2013-1-5 下午05:32:53
	 ********************************************************
	 */
	public String getNameShort(){
		return entity_name.substring(0,1).toLowerCase()+entity_name.substring(1,entity_name.length());
	}
	
	/**
	 * ********************************************************
	 * @Title: createFile
	 * @Description: 生成文件夹
	 * @param file void
	 * @date 2013-1-5 下午04:25:32
	 ********************************************************
	 */
	private void createFile(String fileway){
		File file = new File(fileway);
		boolean rst=file.mkdirs();
		boolean rst1=file.mkdir();
		if(rst==false||rst1==false){
			try {
				throw new Exception("createFile 出错");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * ********************************************************
	 * @Title: writeEntity
	 * @Description: 写入实体类
	 * @param file  文件路径
	 * @param string 写入内容
	 * @date 2012-12-27 下午04:24:49
	 ********************************************************
	 */
	private void writeEntity(String file, String string) {
		
		try {
			FileOutputStream outs = new FileOutputStream(file, false);
			PrintStream p = new PrintStream(outs);
			p.println(string);
			p.flush();
			p.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	private void sqlmapper(){
		
		StringBuffer insert = new StringBuffer("insert into "+entity_name+"(");
		StringBuffer insert_val = new StringBuffer(" values(");
		StringBuffer update = new StringBuffer("update "+entity_name+" set ");
			
		for (Map.Entry<String, String[][]> h_m : hibernate_map.entrySet()) {
			if (!h_m.getKey().equalsIgnoreCase("id")) {
				insert.append(h_m.getKey()+",");
				insert_val.append("#{"+h_m.getKey()+"},");
				update.append(h_m.getKey()+"=#{"+h_m.getKey()+"},");
			}
		}
		insert.deleteCharAt(insert.length()-1);
		insert.append(")");
		insert_val.deleteCharAt(insert_val.length()-1);
		insert_val.append(")");
		update.deleteCharAt(update.length()-1);
		update.append(" where Id=#{id}");
		
		String paks = pak+".sqlmapper";
		StringBuffer entity_txt = new StringBuffer("");      //要生成的信息
		String newFile = locahost+paks.replace(".","\\")+"\\"+entity_name+".xml";           //要生成的实体类
		entity_txt.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?> ").append("\r\n"); 
		entity_txt.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"").append("\r\n");
		entity_txt.append("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append("\r\n");
		entity_txt.append("<mapper namespace=\""+entity_name+"\">").append("\r\n");
		entity_txt.append("	<select id=\"getPageList\" resultType=\"map\" parameterType=\"Page\">").append("\r\n");
		entity_txt.append("		select * from "+table_name+" where 1=1").append("\r\n");
		entity_txt.append("		<if test=\"params.Id!=null and params.Id!=''\">").append("\r\n");
		entity_txt.append("			and Id = '${params.Id}'").append("\r\n");
		entity_txt.append("		</if>").append("\r\n");
		entity_txt.append("	</select>").append("\r\n");
		entity_txt.append("	<select id=\"getOne\" resultType=\""+pak+".entity."+entity_name+"\" parameterType=\"int\">").append("\r\n");
		entity_txt.append("		select * from "+table_name+" where Id=#{id}").append("\r\n");
		entity_txt.append("	</select>").append("\r\n");
		entity_txt.append("	<insert id=\"insert\">").append("\r\n");
		entity_txt.append("	"+insert.toString()).append("\r\n");
		entity_txt.append("	"+insert_val.toString()).append("\r\n");
		entity_txt.append("	</insert>").append("\r\n");
		entity_txt.append("	<update id=\"update\"  parameterType=\"int\">").append("\r\n");
		entity_txt.append("	"+update.toString()).append("\r\n");
		entity_txt.append("	</update>").append("\r\n");
		entity_txt.append("	<delete id=\"delete\" parameterType=\"int\">").append("\r\n");
		entity_txt.append("		delete from "+table_name+" where Id=#{id}").append("\r\n");
		entity_txt.append("	</delete>").append("\r\n");
		entity_txt.append("</mapper> ").append("\r\n");
		
		writeEntity(newFile ,entity_txt.toString());
	}
	
	private void entity(){
		String paks = pak+".entity";
		StringBuffer entity_txt = new StringBuffer("\r\n");      //要生成的信息
		String newFile = locahost+paks.replace(".","\\")+"\\"+entity_name+".java";           //要生成的实体类
		entity_txt.append("package ").append(paks).append(";\r\n");    //包名
		entity_txt.append("\r\n");
		entity_txt.append("import ").append("java.util.Date").append(";\r\n");
		entity_txt.append("\r\n");
		
		//表注释
		entity_txt.append("/**").append("\r\n");
		entity_txt.append("* ********************************************************").append("\r\n");
		entity_txt.append("* @ClassName: ").append(entity_name).append("\r\n"); 
		entity_txt.append("* @Description: ").append(comments_table).append("\r\n"); 
		entity_txt.append("* @author 自动生成").append("\r\n"); 
		entity_txt.append("* @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");      
		entity_txt.append("*******************************************************").append("\r\n");
		entity_txt.append("*/").append("\r\n");
  

		entity_txt.append("public class ").append(entity_name).append(" {").append("\r\n");            //类开始
		entity_txt.append("\r\n");
		//  ----------- 属性	
		for (Map.Entry<String, String[][]> h_m : hibernate_map.entrySet()) {
			entity_txt.append("\t").append("private ").append(h_m.getValue()[0][1]).append(" ").append(h_m.getValue()[0][0]).append(";");;    //属性信息
			entity_txt.append("\t\t//").append(comment_map.get(h_m.getKey())).append("\r\n");      //说明信息
		}
		entity_txt.append("\r\n");
		// -------------- get 和 set 方法
		for (Map.Entry<String, String[][]> h_m : hibernate_map.entrySet()) {
			
			String name = h_m.getValue()[0][0];       //属性名  
			String m_name = name.substring(0,1).toUpperCase()+name.substring(1,name.length());      //首字母大写 属性名
			String type = h_m.getValue()[0][1];       // 数据类型
			String t_name = h_m.getKey();           // 在数据库里的字段名
			
			// get 方法
			entity_txt.append("\t").append("public ").append(type).append(" get").append(m_name).append("() {").append("\r\n");
			entity_txt.append("\t\t").append("return this.").append(name).append(";\r\n");
			entity_txt.append("\t").append("}").append("\r\n");
			entity_txt.append("\r\n");
			// set 方法 
			entity_txt.append("\t").append("public void").append(" set").append(m_name).append("(").append(type).append(" ").append(name).append(") {").append("\r\n");
			entity_txt.append("\t\t").append("this.").append(name).append(" = ").append(name).append(";\r\n");
			entity_txt.append("\t").append("}").append("\r\n");
			entity_txt.append("\r\n");
		}
		
		entity_txt.append("}").append("\r\n");          //类结束
		
		writeEntity(newFile ,entity_txt.toString());
	}
	
	
	private void dao(){
		String paks = pak+".dao";
		StringBuffer entity_txt = new StringBuffer("\r\n");      //要生成的信息
		String newFile = locahost+paks.replace(".","\\")+"\\"+entity_name+"Dao.java";           //要生成的实体类
		entity_txt.append("package ").append(paks).append(";\r\n");    //包名
		entity_txt.append("\r\n");
		entity_txt.append("import ").append(pak+".entity."+entity_name).append(";\r\n");
		entity_txt.append("\r\n");
		
		//表注释
		entity_txt.append("/**").append("\r\n");
		entity_txt.append("* ********************************************************").append("\r\n");
		entity_txt.append("* @ClassName: ").append(entity_name).append("Dao\r\n"); 
		entity_txt.append("* @Description: ").append(comments_table).append("\r\n"); 
		entity_txt.append("* @author 自动生成").append("\r\n"); 
		entity_txt.append("* @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");      
		entity_txt.append("*******************************************************").append("\r\n");
		entity_txt.append("*/").append("\r\n");
   
		entity_txt.append("public interface ").append(entity_name).append("Dao extends BaseDao<"+entity_name+",Integer>{").append("\r\n");            //类开始
		entity_txt.append("\r\n");

		
		entity_txt.append("}").append("\r\n");          //类结束
		
		writeEntity(newFile ,entity_txt.toString());
	}
	
	private void daoImpl(){
		 

		String paks = pak+".dao.impl";
		StringBuffer entity_txt = new StringBuffer("\r\n");      //要生成的信息
		String newFile = locahost+paks.replace(".","\\")+"\\"+entity_name+"DaoImpl.java";           //要生成的实体类
		entity_txt.append("package ").append(paks).append(";\r\n");    //包名
		entity_txt.append("\r\n");
		
		entity_txt.append("import ").append(pak+".entity."+entity_name).append(";\r\n");
		entity_txt.append("import ").append(pak+".dao."+entity_name+"Dao").append(";\r\n");
		entity_txt.append("import ").append("org.springframework.stereotype.Repository").append(";\r\n");
		entity_txt.append("\r\n");
		
		//表注释
		entity_txt.append("/**").append("\r\n");
		entity_txt.append("* ********************************************************").append("\r\n");
		entity_txt.append("* @ClassName: ").append(entity_name).append("DaoImpl\r\n"); 
		entity_txt.append("* @Description: ").append(comments_table).append("\r\n"); 
		entity_txt.append("* @author 自动生成").append("\r\n"); 
		entity_txt.append("* @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");      
		entity_txt.append("*******************************************************").append("\r\n");
		entity_txt.append("*/").append("\r\n");
   
		entity_txt.append("@Repository").append("\r\n");
		entity_txt.append("public class ").append(entity_name).append("DaoImpl extends BaseDaoImpl<"+entity_name+",Integer> implements ").append(entity_name+"Dao").append("{").append("\r\n");            //类开始
		entity_txt.append("\r\n");

		
		entity_txt.append("}").append("\r\n");          //类结束
		
		writeEntity(newFile ,entity_txt.toString());
	}
	
	
	private void service(){
		String paks = pak+".service";
		StringBuffer entity_txt = new StringBuffer("\r\n");      //要生成的信息
		String newFile = locahost+paks.replace(".","\\")+"\\"+entity_name+"Service.java";           //要生成的实体类
		entity_txt.append("package ").append(paks).append(";\r\n");    //包名
		entity_txt.append("\r\n");
		entity_txt.append("import ").append(pak+".entity."+entity_name).append(";\r\n");
		entity_txt.append("\r\n");
  
		
		//表注释
		entity_txt.append("/**").append("\r\n");
		entity_txt.append("* ********************************************************").append("\r\n");
		entity_txt.append("* @ClassName: ").append(entity_name).append("Service\r\n"); 
		entity_txt.append("* @Description: ").append(comments_table).append("\r\n"); 
		entity_txt.append("* @author 自动生成").append("\r\n"); 
		entity_txt.append("* @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");      
		entity_txt.append("*******************************************************").append("\r\n");
		entity_txt.append("*/").append("\r\n");
   
		entity_txt.append("public interface ").append(entity_name).append("Service extends BaseService<"+entity_name+",Integer>{").append("\r\n");            //类开始
		entity_txt.append("\r\n");

		
		entity_txt.append("}").append("\r\n");          //类结束
		
		writeEntity(newFile ,entity_txt.toString());
	}
	
	private void serviceImpl(){
		
		String paks = pak+".service.impl";
		StringBuffer entity_txt = new StringBuffer("\r\n");      //要生成的信息
		String newFile = locahost+paks.replace(".","\\")+"\\"+entity_name+"ServiceImpl.java";           //要生成的实体类
		entity_txt.append("package ").append(paks).append(";\r\n");    //包名
		entity_txt.append("\r\n");
		entity_txt.append("import ").append(pak+".entity."+entity_name).append(";\r\n");
		entity_txt.append("import ").append(pak+".dao."+entity_name+"Dao").append(";\r\n");
		entity_txt.append("import ").append(pak+".service."+entity_name+"Service").append(";\r\n");		
 		entity_txt.append("import ").append("javax.annotation.Resource").append(";\r\n");
		entity_txt.append("import ").append("org.springframework.stereotype.Service").append(";\r\n");
		entity_txt.append("\r\n");
		
		//表注释
		entity_txt.append("/**").append("\r\n");
		entity_txt.append("* ********************************************************").append("\r\n");
		entity_txt.append("* @ClassName: ").append(entity_name).append("ServiceImpl\r\n"); 
		entity_txt.append("* @Description: ").append(comments_table).append("\r\n"); 
		entity_txt.append("* @author 自动生成").append("\r\n"); 
		entity_txt.append("* @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");      
		entity_txt.append("*******************************************************").append("\r\n");
		entity_txt.append("*/").append("\r\n");
   
		entity_txt.append("@Service").append("\r\n");
		entity_txt.append("public class ").append(entity_name).append("ServiceImpl extends BaseServiceImpl<"+entity_name+",Integer> implements ").append(entity_name+"Service").append("{").append("\r\n");            //类开始
		entity_txt.append("\r\n");
		
		entity_txt.append("	@Resource").append("\r\n");
		entity_txt.append("	private "+entity_name+ "Dao " +entity_name_short+"Dao").append(";\r\n");
		entity_txt.append("\r\n");
		entity_txt.append("	@Resource").append("\r\n");
		entity_txt.append("	public void setBaseDao("+entity_name+"Dao " + entity_name_short.substring(0,1)+"Dao) {").append("\r\n");
		entity_txt.append("		super.setBaseDao("+entity_name_short+"Dao);").append("\r\n");
		entity_txt.append("	}").append("\r\n");
		
		entity_txt.append("}").append("\r\n");          //类结束
		
		writeEntity(newFile ,entity_txt.toString());
	}
	
	private void action(){
		String paks = pak+".controller";
		StringBuffer entity_txt = new StringBuffer("\r\n");      //要生成的信息
		String newFile = locahost+paks.replace(".","\\")+"\\"+entity_name+"Controller.java";           //要生成的实体类
		entity_txt.append("package ").append(paks).append(";\r\n");    //包名
		entity_txt.append("\r\n");
		entity_txt.append("\r\n");
		entity_txt.append("import ").append("java.util.Map").append(";\r\n");
		entity_txt.append("import ").append(pak+".entity."+entity_name).append(";\r\n");
		entity_txt.append("import ").append(pak+".service."+entity_name+"Service").append(";\r\n");
		entity_txt.append("import ").append("javax.annotation.Resource").append(";\r\n");	
		entity_txt.append("import ").append("org.springframework.context.annotation.Scope").append(";\r\n");		
 		entity_txt.append("import ").append("org.springframework.stereotype.Controller").append(";\r\n");
 		entity_txt.append("import ").append("org.springframework.web.bind.annotation.ModelAttribute").append(";\r\n");
		entity_txt.append("import ").append("org.springframework.web.bind.annotation.RequestMapping").append(";\r\n");
		entity_txt.append("import ").append("org.springframework.web.bind.annotation.RequestParam").append(";\r\n");
		entity_txt.append("import ").append("org.springframework.web.bind.annotation.ResponseBody").append(";\r\n");
		entity_txt.append("\r\n");
		
		//表注释
		entity_txt.append("/**").append("\r\n");
		entity_txt.append("* ********************************************************").append("\r\n");
		entity_txt.append("* @ClassName: ").append(entity_name).append("Controller\r\n"); 
		entity_txt.append("* @Description: ").append(comments_table).append("\r\n"); 
		entity_txt.append("* @author 自动生成").append("\r\n"); 
		entity_txt.append("* @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");      
		entity_txt.append("*******************************************************").append("\r\n");
		entity_txt.append("*/").append("\r\n");
   
	 
		entity_txt.append("@Scope(\"prototype\")").append("\r\n");
		entity_txt.append("@Controller").append("\r\n");
		entity_txt.append("@RequestMapping(\"/"+entity_name+"\")").append("\r\n");
		entity_txt.append("public class ").append(entity_name).append("Controller extends BaseController").append("{").append("\r\n");            //类开始
		entity_txt.append("\r\n");
		
		entity_txt.append("	@Resource");
		entity_txt.append("\r\n");
		entity_txt.append("	private "+entity_name+"Service "+entity_name_short+"Service").append(";\r\n");
		entity_txt.append("\r\n");
		
		// 分页查询方法
		entity_txt.append("	/**").append("\r\n");
		entity_txt.append("	 * ********************************************************").append("\r\n");
		entity_txt.append("	 * @Title: list").append("\r\n");
		entity_txt.append("	 * @Description: 分页,条件查询").append("\r\n");
		entity_txt.append("	 * @return String").append("\r\n");
		entity_txt.append("	 * @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");
		entity_txt.append("	 ********************************************************").append("\r\n");
		entity_txt.append("	 */").append("\r\n");
		entity_txt.append("	 @RequestMapping(\"/list\")").append("\r\n");
		entity_txt.append("	public String list(){").append("\r\n");
		entity_txt.append("		"+entity_name_short+"Service.getPageList(this.getPage())").append(";\r\n");;
		entity_txt.append("		return this.display()").append(";\r\n");
		entity_txt.append("	}").append("\r\n");
		entity_txt.append("").append("\r\n");
		
		// 添加 、 显示方法
		entity_txt.append("	/**").append("\r\n");
		entity_txt.append("	 * ********************************************************").append("\r\n");
		entity_txt.append("	 * @Title: add").append("\r\n");
		entity_txt.append("	 * @Description: 添加、显示").append("\r\n");
		entity_txt.append("	 * @return String").append("\r\n");
		entity_txt.append("	 * @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");
		entity_txt.append("	 ********************************************************").append("\r\n");
		entity_txt.append("	 */").append("\r\n");
		entity_txt.append("	 @RequestMapping(\"/add\")").append("\r\n");
		entity_txt.append("	public String add(@RequestParam(value=\"id\",required=false) Integer id){").append("\r\n");
		entity_txt.append("		if(id!=null){").append("\r\n");
		entity_txt.append("			"+entity_name+" "+entity_name_short+" = "+entity_name_short+"Service.getOne(id) ").append(";\r\n");
		entity_txt.append("			this.setAttribute(\""+entity_name_short+"\","+entity_name_short+")").append(";\r\n");
		entity_txt.append("		}").append("\r\n");
		entity_txt.append("		return this.display()").append(";\r\n");
		entity_txt.append("	}").append("\r\n");
		entity_txt.append("").append("\r\n");
		
		// 修改保存方法
		entity_txt.append("	/**").append("\r\n");
		entity_txt.append("	 * ********************************************************").append("\r\n");
		entity_txt.append("	 * @Title: save").append("\r\n");
		entity_txt.append("	 * @Description: 修改保存").append("\r\n");
		entity_txt.append("	 * @return String").append("\r\n");
		entity_txt.append("	 * @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");
		entity_txt.append("	 ********************************************************").append("\r\n");
		entity_txt.append("	 */").append("\r\n");
		entity_txt.append("	 @RequestMapping(\"/save\")").append("\r\n");
		entity_txt.append("	public @ResponseBody Map save(@ModelAttribute(\""+entity_name+"\") "+entity_name+"  "+entity_name_short+"){").append("\r\n");
		entity_txt.append("		int result="+entity_name_short+".getId()==null?"+entity_name_short+"Service.insert("+entity_name_short+"):"+entity_name_short+"Service.update("+entity_name_short+")").append(";\r\n");
		entity_txt.append("		return success(\"保存[\"+"+entity_name_short+".getId()+\"]成功\",\"\")").append(";\r\n");
		entity_txt.append("	}").append("\r\n");
		entity_txt.append("").append("\r\n");
		
		entity_txt.append("	/**").append("\r\n");
		entity_txt.append("	 * ********************************************************").append("\r\n");
		entity_txt.append("	 * @Title: delete").append("\r\n");
		entity_txt.append("	 * @Description: 删除").append("\r\n");
		entity_txt.append("	 * @return String").append("\r\n");
		entity_txt.append("	 * @date ").append(new SimpleDateFormat("yyyy-MM-dd aa hh:mm:ss ").format(new Date())).append("\r\n");
		entity_txt.append("	 ********************************************************").append("\r\n");
		entity_txt.append("	 */").append("\r\n");
		entity_txt.append("	 @RequestMapping(\"/delete\")").append("\r\n");
		entity_txt.append("	public @ResponseBody Map delete(@RequestParam Integer id){").append("\r\n");
		entity_txt.append("		int result="+entity_name_short+"Service.delete(id)").append(";\r\n");
		entity_txt.append("		return success(\"删除成功\",\"\")").append(";\r\n");
		entity_txt.append("	}").append("\r\n");
		
		entity_txt.append("\r\n");
		entity_txt.append("}").append("\r\n");          //类结束
		
		writeEntity(newFile ,entity_txt.toString());
	}
	
	
	public void list_html(){

		StringBuffer entity_txt = new StringBuffer("\r\n");      //要生成的信息
		entity_txt.append("<#include \"/common/pageForm.ftl\">").append("\r\n");
		entity_txt.append("<div class=\"pageHeader\">").append("\r\n");
		entity_txt.append("	<form onsubmit=\"return navTabSearch(this);\" action=\"${url}\" method=\"post\">").append("\r\n");
		entity_txt.append("		<div class=\"searchBar\">").append("\r\n");
		entity_txt.append("			<table class=\"searchContent\">").append("\r\n");
		entity_txt.append("				<tr>").append("\r\n");
		entity_txt.append("						<td>").append("\r\n");
		entity_txt.append("							ID:<input type=\"text\" name=\"Id\" value=\"${(p.params.xingming)!''}\" size=15/>").append("\r\n");
		entity_txt.append("						</td>").append("\r\n");
		entity_txt.append("						<td>").append("\r\n");
		entity_txt.append("							<div class=\"buttonActive\"><div class=\"buttonContent\"><button type=\"submit\">查询</button></div></div>").append("\r\n");
		entity_txt.append("						</td>").append("\r\n");
		entity_txt.append("				<tr>").append("\r\n");
		entity_txt.append("			</table>").append("\r\n");
		entity_txt.append("		</div>").append("\r\n");
		entity_txt.append("	</form>").append("\r\n");
		entity_txt.append("</div>").append("\r\n");
		entity_txt.append("<div class=\"pageContent\">").append("\r\n");
		entity_txt.append("	<div class=\"panelBar\">").append("\r\n");
		entity_txt.append("		<ul class=\"toolBar\">").append("\r\n");
		entity_txt.append("			<li><a class=\"add\" href=\"${baseClass}/add\" target=\"dialog\" rel=\"BaseinfoClass_add\" width=\"450\"><span>添加</span></a></li>").append("\r\n");
		entity_txt.append("			<li><a class=\"edit\" href=\"${baseClass}/add?id={id}\" target=\"dialog\"  width=\"450\" rel=\"BaseinfoClass_add\" title=\"修改-{class_cn}\"><span>修改</span></a></li>").append("\r\n");
		entity_txt.append("			<li><a class=\"delete\" href=\"${baseClass}/delete?id={id}\" target=\"ajaxTodo\" title=\"确定要删除【{class_cn}】吗?\"><span>删除</span></a></li>").append("\r\n");
		entity_txt.append("		</ul>").append("\r\n");
		entity_txt.append("	</div>").append("\r\n");
		entity_txt.append("	<table class=\"table\" width=\"98%\" layoutH=\"109\">").append("\r\n");
		entity_txt.append("		<thead>").append("\r\n");
		entity_txt.append("			<tr>").append("\r\n");
		// 列头
		for (Map.Entry<String, String[][]> h_m : hibernate_map.entrySet()) {
			entity_txt.append("				<th width=\"10%\" orderField=\""+(h_m.getKey().equals("id")?"Id":h_m.getKey())+"\">"+comment_map.get(h_m.getKey())+"</th>").append("\r\n");
		}
		
		entity_txt.append("			</tr>").append("\r\n");
		entity_txt.append("		</thead>").append("\r\n");
		entity_txt.append("		<tbody>").append("\r\n");
		entity_txt.append("			<#list p.results as list>").append("\r\n");
		entity_txt.append("				<tr target=\"id\" rel=\"${list.Id}\">").append("\r\n");
		// 列值
		for (Map.Entry<String, String[][]> h_m : hibernate_map.entrySet()) {
			entity_txt.append("					<td >${(list."+(h_m.getKey().equals("id")?"ID":h_m.getKey()).toUpperCase()+")!}</td>").append("\r\n");
		}
		
		entity_txt.append("				</tr>").append("\r\n");
		entity_txt.append("			</#list>").append("\r\n");
		entity_txt.append("		</tbody>").append("\r\n");
		entity_txt.append("	</table>").append("\r\n");
		entity_txt.append("	<#include \"/common/pages.ftl\">").append("\r\n");
		entity_txt.append("</div>").append("\r\n");
		 
 
		String file = address+webHtml+entity_name+"\\";
		createFile(file);      //创建文件夹
		writeEntity(file+"list.ftl" ,entity_txt.toString());        //写入代码
		
	}
	
	public void find_html(){
		
		StringBuffer entity_txt = new StringBuffer("\r\n");      //要生成的信息
		entity_txt.append("<div class=\"pageContent\" style=\"overflow:hidden;\">").append("\r\n");
		entity_txt.append("	<form method=\"post\" action=\"${baseClass}/save\" class=\"pageForm required-validate\" onsubmit=\"return validateCallback(this, dialogAjaxDone);\">").append("\r\n");
		entity_txt.append("		<div class=\"pageFormContent\" layoutH=\"50\" style=\"background-color:#ecf2f4;overflow:hidden;padding-bottom:0\">").append("\r\n");
		entity_txt.append("			<fieldset>").append("\r\n");
		
		for (Map.Entry<String, String[][]> h_m : hibernate_map.entrySet()) {
			entity_txt.append("				<p>").append("\r\n");
			entity_txt.append("					<label style=\"width:60px;\">"+comment_map.get(h_m.getKey())+"</label>").append("\r\n");
			entity_txt.append("					<input type=\"text\" name=\""+h_m.getValue()[0][0]+"\" value=\"${("+entity_name_short+"."+h_m.getValue()[0][0]+")!}\"/>").append("\r\n");
			entity_txt.append("				</p>").append("\r\n");
		}
		entity_txt.append("				<input type=\"hidden\" name=\"id\" value=\"${(baseinfoClass.id)!}\">").append("\r\n");
		entity_txt.append("			</fieldset>").append("\r\n");
		entity_txt.append("		</div>").append("\r\n");
		entity_txt.append("		<div class=\"formBar\">").append("\r\n");
		entity_txt.append("			<ul>").append("\r\n");
		entity_txt.append("				<li><div class=\"buttonActive\"><div class=\"buttonContent\"><button type=\"submit\">保存</button></div></div></li>").append("\r\n");
		entity_txt.append("				<li><div class=\"button\"><div class=\"buttonContent\"><button type=\"button\" class=\"close\">取消</button></div></div></li>").append("\r\n");
		entity_txt.append("			</ul>").append("\r\n");
		entity_txt.append("		</div>").append("\r\n");
		entity_txt.append("	</form>").append("\r\n");
		entity_txt.append("</div>").append("\r\n");
 
		String file = address+webHtml+entity_name+"\\";
		createFile(file);      //创建文件夹
		writeEntity(file+"add.ftl" ,entity_txt.toString());        //写入代码
	}
	
	public void add_sqlmapper() throws ExceptionDeal{
		String paks = pak+".sqlmapper";
		StringBuffer entity_txt = new StringBuffer("");      //要生成的信息
		String newFile = locahost+paks.replace(".","\\")+"\\sqlMapConfig.xml";           //要生成的实体类
		File file=new File(newFile);
		FileReader fileReader=null;
		try {
			 fileReader=new FileReader(file);
			BufferedReader txtF = new BufferedReader(fileReader);
			String  line = "";
			while ((line = txtF.readLine()) != null) { 
				
				if (line.indexOf("</mappers>")  >= 0) {
					entity_txt.append("	  <mapper resource=\"com/carnival/sqlmapper/"+entity_name+".xml\"/>").append("\r\n");
				}
				entity_txt.append(line).append("\r\n");
			}
		}catch (Exception e) {
			
			throw new ExceptionDeal(e);
		}finally{
			try {
				fileReader.close();//关闭fileReader
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		writeEntity(newFile ,entity_txt.toString());
	}
}

	
