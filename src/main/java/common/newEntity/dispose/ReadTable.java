package common.newEntity.dispose;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
 

/**
 * ********************************************************
 * @ClassName: ReadTable
 * @Description: 读取表属性
 * @author DoDo
 * @date 2012-12-26 下午05:36:42
 *******************************************************
 */
@SuppressWarnings("all")
public class ReadTable {
	
	
	private String DRIVER = "";
	private String URL = "";
	private String USENAME = "";
	private String USEPASS = "";
	
 
	/**
	 * 获取连接 
	 */
	public Connection getConn(){
		
		ClassPathResource cr = new ClassPathResource("dataSource.properties");//会重新加载spring框架
        Properties pros = new Properties();
        try {
            pros.load(cr.getInputStream());
        } catch (IOException ex) {
 
        }
       
        DRIVER = pros.get("driver").toString();
        URL = pros.get("url").toString();
        USENAME = pros.get("username").toString();
        USEPASS = pros.get("password").toString();
 
		 Connection conn = null; 
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USENAME,USEPASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return conn;
	}
	
	/**
	 * ********************************************************
	 * @Title: read
	 * @Description: 获取表字段名和数据类型
	 * @param table
	 * @return Map
	 * @date 2012-12-26 下午05:37:20
	 ********************************************************
	 */
	public Map read(String table) {

		Map<String,String[][]> map = new HashMap<String, String[][]>();
		Connection conn = getConn();
		PreparedStatement pstmt = null;
		ResultSetMetaData rsmd = null;
		String findsql = "select * from " + table;    
		try {
			pstmt = conn.prepareStatement(findsql);
			pstmt.execute();    // 这点特别要注意:如果是Oracle  而对于mysql可以不用加.
			rsmd = (ResultSetMetaData) pstmt.getMetaData();
			
			for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
			    map.put(rsmd.getColumnName(i).toLowerCase(),new String[][]{{rsmd.getColumnTypeName(i).toLowerCase(),rsmd.getScale(i)+""}});
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				pstmt.close();			
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		return map;
	}
	
	
	
	/**
	 * ********************************************************
	 * @Title: findDecimals
	 * @Description: 查询小数位数
	 * @param table
	 * @param attribute
	 * @return boolean
	 * @date 2012-12-27 上午11:49:35
	 ********************************************************
	 */
	public int findDecimals(String table,String attribute){
		
		String sql = "";
		if (DRIVER.toUpperCase().indexOf("ORACLE") >= 0) {
			sql = "SELECT nvl(data_scale,0) FROM ALL_TAB_COLUMNS WHERE OWNER= (select default_tablespace from user_users) and data_type = 'NUMBER' and table_name = '"+table.toUpperCase()+"'  and column_name = '"+attribute.toUpperCase()+"' ";  //ORACLE
		}else if(DRIVER.toUpperCase().indexOf("MYSQL") >= 0){
			sql = "select ifnull(numeric_scale,0) from information_schema.columns where table_schema = (SELECT database()) and table_name = '"+table+"' and column_name = '"+attribute+"'";   //MYSQL
		}

		Connection conn = getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return num;
		}finally{
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		return num;
	}
	
	
	/**
	 * ********************************************************
	 * @Title: findTableComments
	 * @Description: 查询 Oracle 数据库 Comments 表说明
	 * @param table     表名 
	 * @return String 
	 * @date 2012-12-27 下午05:55:44
	 ********************************************************
	 */
	public String findTableComments(String table){
		String sql = "";
		if (DRIVER.toUpperCase().indexOf("ORACLE") >= 0) {
			sql = "select nvl(comments,'') from user_tab_comments where table_name='"+table.toUpperCase()+"'";  //ORACLE 
		}else if(DRIVER.toUpperCase().indexOf("MYSQL") >= 0){
			sql = "SELECT substring_index(table_comment,';',1) from Information_schema.TABLES where table_schema = (SELECT database()) and  table_name = '"+table+"'";  //MYSQL
		}
		Connection conn = getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String comments = "";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				comments = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}finally{
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		return comments;
	}
	
	/**
	 * ********************************************************
	 * @Title: findColComment
	 * @Description: 查询该表所有属性的说明
	 * @param table
	 * @return Map
	 * @date 2012-12-27 下午05:59:25
	 ********************************************************
	 */
	public Map findColComment(String table) {
		Map<String,String> map = new HashMap<String, String>();
		Connection conn = getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String findsql = "";
		if (DRIVER.toUpperCase().indexOf("ORACLE") >= 0) {
			findsql = "select column_name,nvl(comments,'') from user_col_comments where table_name='"+table.toUpperCase()+"'";    //ORACLE
		}else if(DRIVER.toUpperCase().indexOf("MYSQL") >= 0){
			findsql = "select column_name,ifnull(column_comment,'') from information_schema.columns where table_schema = (SELECT database()) and table_name = '"+table+"'";    //MYSQL
		}

		try {
			pstmt = conn.prepareStatement(findsql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				map.put(rs.getString(1).toLowerCase(), rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				pstmt.close();
				rs.close();				
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		return map;
	}
}
