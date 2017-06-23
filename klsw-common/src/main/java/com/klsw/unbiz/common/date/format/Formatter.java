package com.klsw.unbiz.common.date.format;

import com.klsw.unbiz.common.date.DateTimeStamp;
import com.klsw.unbiz.common.date.DatetimeObject;

/**
 * 
 * @author <a href="mailto:xuchen06@baidu.com">xuc</a>
 * @version create on 2014年9月22日 下午5:14:34
 */
public interface Formatter {

    String convert(DatetimeObject datetime, String format);

    DateTimeStamp parse(String value, String format);
}
