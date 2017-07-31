package com.wondersgroup.sh.search.params;

import java.util.Iterator;
import java.util.Map;
import java.io.IOException;

import com.wondersgroup.sh.search.util.StringUtil;

public class MapWdisParams extends WdisParams {
  protected final Map<String,String> map;

  public MapWdisParams(Map<String,String> map) {
    this.map = map;
  }

  @Override
  public String get(String name) {
    return map.get(name);
  }

  @Override
  public String[] getParams(String name) {
    String val = map.get(name);
    return val==null ? null : new String[]{val};
  }

  @Override
  public Iterator<String> getParameterNamesIterator() {
    return map.keySet().iterator();
  }

  public Map<String,String> getMap() { return map; }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(128);
    try {
      boolean first=true;

      for (Map.Entry<String,String> entry : map.entrySet()) {
        String key = entry.getKey();
        String val = entry.getValue();

        if (!first) sb.append('&');
        first=false;
        sb.append(key);
        sb.append('=');
        StringUtil.partialURLEncodeVal(sb, val==null ? "" : val);
      }
    }
    catch (IOException e) {throw new RuntimeException(e);}  // can't happen

    return sb.toString();
  }
}
