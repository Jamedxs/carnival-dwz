package common.newEntity;

import com.carnival.util.ExceptionDeal;
import common.newEntity.dispose.WriteEntity;

/**
 * ********************************************************
 * @ClassName: newEntity
 * @Description: 自动生成类方法  ， 只要写入表名即可
 * @author DoDo
 * @date 2012-12-27 下午06:29:37
 *******************************************************
 */
@SuppressWarnings("all")
public class NewEntity {


	static String table_name = "system_notification";              //要创建的表明

	private void syso() {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) throws ExceptionDeal {
		new WriteEntity().write(table_name.trim());
	}
	
}
