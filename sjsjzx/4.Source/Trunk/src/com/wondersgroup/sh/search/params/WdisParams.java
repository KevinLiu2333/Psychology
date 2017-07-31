package com.wondersgroup.sh.search.params;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.util.NamedList;
import com.wondersgroup.sh.search.util.SimpleOrderedMap;
import com.wondersgroup.sh.search.util.StringUtil;

public abstract class WdisParams implements Serializable {
	private static final long serialVersionUID = -4184471419475887015L;

	/** returns the String value of a param, or null if not set */
  public abstract String get(String param);

  /** returns an array of the String values of a param, or null if none */
  public abstract String[] getParams(String param);

  /** returns an Iterator over the parameter names */
  public abstract Iterator<String> getParameterNamesIterator();

  /** returns the value of the param, or def if not set */
  public String get(String param, String def) {
    String val = get(param);
    return val==null ? def : val;
  }
  
  /** returns a RequiredSolrParams wrapping this */
  public RequiredWdisParams required()
  {
    // TODO? should we want to stash a reference?
    return new RequiredWdisParams(this);
  }
  
  protected String fpname(String field, String param) {
    return "f."+field+'.'+param;
  }

  /** returns the String value of the field parameter, "f.field.param", or
   *  the value for "param" if that is not set.
   */
  public String getFieldParam(String field, String param) {
    String val = get(fpname(field,param));
    return val!=null ? val : get(param);
  }

  /** returns the String value of the field parameter, "f.field.param", or
   *  the value for "param" if that is not set.  If that is not set, def
   */
  public String getFieldParam(String field, String param, String def) {
    String val = get(fpname(field,param));
    return val!=null ? val : get(param, def);
  }
  
  /** returns the String values of the field parameter, "f.field.param", or
   *  the values for "param" if that is not set.
   */
  public String[] getFieldParams(String field, String param) {
    String[] val = getParams(fpname(field,param));
    return val!=null ? val : getParams(param);
  }

  /** Returns the Boolean value of the param, or null if not set */
  public Boolean getBool(String param) {
    String val = get(param);
    return val==null ? null : StringUtil.parseBool(val);
  }

  /** Returns the boolean value of the param, or def if not set */
  public boolean getBool(String param, boolean def) {
    String val = get(param);
    return val==null ? def : StringUtil.parseBool(val);
  }
  
  /** Returns the Boolean value of the field param, 
      or the value for param, or null if neither is set. */
  public Boolean getFieldBool(String field, String param) {
    String val = getFieldParam(field, param);
    return val==null ? null : StringUtil.parseBool(val);
  }
  
  /** Returns the boolean value of the field param, 
  or the value for param, or def if neither is set. */
  public boolean getFieldBool(String field, String param, boolean def) {
    String val = getFieldParam(field, param);
    return val==null ? def : StringUtil.parseBool(val);
  }

  /** Returns the Integer value of the param, or null if not set */
  public Integer getInt(String param) {
    String val = get(param);
    try {
      return val==null ? null : Integer.valueOf(val);
    }
    catch( Exception ex ) {
      throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, ex.getMessage(), ex );
    }
  }

  /** Returns the int value of the param, or def if not set */
  public int getInt(String param, int def) {
    String val = get(param);
    try {
      return val==null ? def : Integer.parseInt(val);
    }
    catch( Exception ex ) {
      throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, ex.getMessage(), ex );
    }
  }
  
  /**
   * @return The int value of the field param, or the value for param 
   * or <code>null</code> if neither is set. 
   **/
  public Integer getFieldInt(String field, String param) {
    String val = getFieldParam(field, param);
    try {
      return val==null ? null : Integer.valueOf(val);
    }
    catch( Exception ex ) {
      throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, ex.getMessage(), ex );
    }
  }
  
  /** Returns the int value of the field param, 
  or the value for param, or def if neither is set. */
  public int getFieldInt(String field, String param, int def) {
    String val = getFieldParam(field, param);
    try {
      return val==null ? def : Integer.parseInt(val);
    }
    catch( Exception ex ) {
      throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, ex.getMessage(), ex );
    }
  }


  /** Returns the Float value of the param, or null if not set */
  public Float getFloat(String param) {
    String val = get(param);
    try {
      return val==null ? null : Float.valueOf(val);
    }
    catch( Exception ex ) {
      throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, ex.getMessage(), ex );
    }
  }

  /** Returns the float value of the param, or def if not set */
  public float getFloat(String param, float def) {
    String val = get(param);
    try {
      return val==null ? def : Float.parseFloat(val);
    }
    catch( Exception ex ) {
      throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, ex.getMessage(), ex );
    }
  }

  /** Returns the float value of the field param. */
  public Float getFieldFloat(String field, String param) {
    String val = getFieldParam(field, param);
    try {
      return val==null ? null : Float.valueOf(val);
    }
    catch( Exception ex ) {
      throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, ex.getMessage(), ex );
    }
  }

  /** Returns the float value of the field param,
  or the value for param, or def if neither is set. */
  public float getFieldFloat(String field, String param, float def) {
    String val = getFieldParam(field, param);
    try {
      return val==null ? def : Float.parseFloat(val);
    }
    catch( Exception ex ) {
      throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, ex.getMessage(), ex );
    }
  }
  
  /** how to transform a String into a boolean... more flexible than
   * Boolean.parseBoolean() to enable easier integration with html forms.
   */
  @Deprecated
  protected boolean parseBool(String s) {
    return StringUtil.parseBool(s);
  }

  /** Create a Map<String,String> from a NamedList given no keys are repeated */
  public static Map<String,String> toMap(NamedList params) {
    HashMap<String,String> map = new HashMap<String,String>();
    for (int i=0; i<params.size(); i++) {
      map.put(params.getName(i), params.getVal(i).toString());
    }
    return map;
  }

  /** Create a Map<String,String[]> from a NamedList */
  public static Map<String,String[]> toMultiMap(NamedList params) {
    HashMap<String,String[]> map = new HashMap<String,String[]>();
    for (int i=0; i<params.size(); i++) {
      String name = params.getName(i);
      String val = params.getVal(i).toString();
      MultiMapWdisParams.addParam(name,val,map);
    }
    return map;
  }

  /** Create WdisParams from NamedList. */
  public static WdisParams toSolrParams(NamedList params) {
    // if no keys are repeated use the faster MapSolrParams
    HashMap<String,String> map = new HashMap<String,String>();
    for (int i=0; i<params.size(); i++) {
      String prev = map.put(params.getName(i), params.getVal(i).toString());
      if (prev!=null) return new MultiMapWdisParams(toMultiMap(params));
    }
    return new MapWdisParams(map);
  }
  
  /** Convert this to a NamedList */
  public NamedList<Object> toNamedList() {
    final SimpleOrderedMap result = new SimpleOrderedMap();
    
    for(Iterator<String> it=getParameterNamesIterator(); it.hasNext(); ) {
      final String name = it.next();
      final String [] values = getParams(name);
      if(values.length==1) {
        result.add(name,values[0]);
      } else {
        // currently no reason not to use the same array
        result.add(name,values);
      }
    }
    return result;
  }
}









