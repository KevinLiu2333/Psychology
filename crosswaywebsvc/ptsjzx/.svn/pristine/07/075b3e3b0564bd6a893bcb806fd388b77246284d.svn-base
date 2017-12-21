package com.wonders.tiles.workflow.util;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

/**
 * 扩展Apache Commons BeanUtils, 提供一些反射方面缺失的封装.
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
    /**
     * 覆写org.apache.commons.beanutils.BeanUtils的copyProperties方法
     */
    public static void copyProperties(Object dest, Object orig) {

        registerConverter();

        try {
            BeanUtilsBean.getInstance2().copyProperties(dest, orig);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册类型转换，可以避免两个问题：
     * 
     * 1、对于数值型，当源对象中的字段为null，避免转换成0等；
     * 
     * 2、增加java.util.Date的类型转换，格式为“yyyy-MM-dd”；
     */
    public static void registerConverter() {
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new FloatConverter(null), Float.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
        ConvertUtils.register(new SqlTimestampConverter(null), Timestamp.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
    }
}
