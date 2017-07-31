package com.wondersgroup.sh.search.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/** <code>SimpleOrderedMap</code> is a {@link NamedList} where access by key is more
 * important than maintaining order when it comes to representing the
 * held data in other forms, as ResponseWriters normally do.
 * It's normally not a good idea to repeat keys or use null keys, but this
 * is not enforced.  If key uniqueness enforcement is desired, use a regular {@link Map}.
 * <p>
 * For example, a JSON response writer may choose to write a SimpleOrderedMap
 * as {"foo":10,"bar":20} and may choose to write a NamedList as
 * ["foo",10,"bar",20].  An XML response writer may choose to render both
 * the same way.
 * </p>
 * <p>
 * This class does not provide efficient lookup by key, it's main purpose is
 * to hold data to be serialized.  It aims to minimize overhead and to be
 * efficient at adding new elements.
 * </p>
 */
public class SimpleOrderedMap<T> extends NamedList<T> {
	private static final long serialVersionUID = 1074897590543828159L;

	/** Creates an empty instance */
  public SimpleOrderedMap() {
    super();
  }

  /**
   * Creates an instance backed by an explicitly specified list of
   * pairwise names/values.
   *
   * @param nameValuePairs underlying List which should be used to implement a SimpleOrderedMap; modifying this List will affect the SimpleOrderedMap.
   */
  @Deprecated
  public SimpleOrderedMap(List nameValuePairs) {
    super(nameValuePairs);
  }
  
  public SimpleOrderedMap(Map.Entry<String, T>[] nameValuePairs) { 
    super(nameValuePairs);
  }

  @Override
  public SimpleOrderedMap<T> clone() {
    ArrayList newList = new ArrayList(nvPairs.size());
    newList.addAll(nvPairs);
    return new SimpleOrderedMap<T>(newList);
  }
}