package com.wondersgroup.sh.search.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ResourceLoader
{
  public InputStream openResource(String resource) throws IOException;
  
  /**
   * Accesses a resource by name and returns the (non comment) lines
   * containing data.
   *
   * <p>
   * A comment line is any line that starts with the character "#"
   * </p>
   *
   * @param resource
   * @return a list of non-blank non-comment lines with whitespace trimmed
   * from front and back.
   * @throws IOException
   */
  public List<String> getLines(String resource) throws IOException;
  
  public Object newInstance(String cname, String ... subpackages);
}
