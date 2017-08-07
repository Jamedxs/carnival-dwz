
package com.carnival.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDeal extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	//有参构造方法 className 操作类名 msg信息
	public ExceptionDeal(Exception e)  {
		 StringWriter sw = new StringWriter();
         e.printStackTrace(new PrintWriter(sw, true));
         String message = sw.toString();
         //保存日志
         Utils.saveErrorLog(message);
    }
}

	