package com.carnival.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@SuppressWarnings("all")
public class XssRequestWrapper extends HttpServletRequestWrapper {
		
	public XssRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public String[] getParameterValues(String parameter) {
	      String[] values = super.getParameterValues(parameter);
	      if (values==null)  {
                  return null;
	          }
	      int count = values.length;
	      String[] encodedValues = new String[count];
	      for (int i = 0; i < count; i++) {
	           encodedValues[i] = cleanXSS(values[i]);
	       }
	      return encodedValues;
	    }
	
    public String getParameter(String parameter) {
          String value = super.getParameter(parameter);
          if (value == null) {
             return null;
          }
          return cleanXSS(value);
    }
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return cleanXSS(value);
    }
    private String cleanXSS(String value) {
    	boolean n= value.matches("^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$"); 
        if(n=true){
        	return value;
        }
    	String matchStr = "'|and|exec|execute|insert|select" +
    			"|delete|update|count|drop|%|chr|mid|master|truncate|"+ 
    	        "char|declare|sitename|net user|xp_cmdshell|;|or|-|like'|and|exec|execute|insert|create|drop|"+
    	        "table|from|grant|use|group_concat|column_name|" +
    	        "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|"+ 
    	        "chr|mid|master|truncate|char|declare|or|;|-|--|like|//|/|%|#|\\+|\\*";
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        value = value.replaceAll("(?:"+matchStr+")", "");
        return value;
    }
       
}