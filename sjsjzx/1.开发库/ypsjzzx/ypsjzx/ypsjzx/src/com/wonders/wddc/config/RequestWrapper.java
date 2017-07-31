package com.wonders.wddc.config;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
  
public final class RequestWrapper extends HttpServletRequestWrapper {   
       
    public RequestWrapper(HttpServletRequest servletRequest) {   
        super(servletRequest);   
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
    	value=value.replaceAll("&","&amp;"); 
    	value=value.replaceAll("<","&lt;"); 
    	value=value.replaceAll(">","&gt;"); 
    	value=value.replaceAll("'","&#039;");
    	value=value.replaceAll("\"","&quot;"); 
        return value;   
    }   
}  