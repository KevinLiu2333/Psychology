package com.wonders.tiles.workflow.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * java.util.Date转换类，默认格式yyyy-MM-dd,当格式不符时返回null
 */
@SuppressWarnings("unchecked")
public class DateConverter implements Converter {
    private Object defaultValue = null;

    private boolean useDefault = true;

    public DateConverter() {

        this.defaultValue = null;
        this.useDefault = false;

    }

    public DateConverter(Object defaultValue) {

        this.defaultValue = defaultValue;
        this.useDefault = true;

    }

    public Object convert(Class type, Object value) {

        if (value == null) {
            if (useDefault) {
                return (defaultValue);
            } else {
                throw new ConversionException("No value specified");
            }
        }

        if (value instanceof Date) {
            return (value);
        }

        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(value.toString());
        } catch (Exception e) {
            if (useDefault) {
                return (defaultValue);
            } else {
                throw new ConversionException(e);
            }
        }

    }

}
