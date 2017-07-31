package com.wonders.tiles.workflow.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.ContextClassLoaderLocal;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 反射装载类处理类 ,重写apache的BeanUtilsBean类的实现.
 *
 */
@SuppressWarnings("unchecked")
public class BeanUtilsBean extends org.apache.commons.beanutils.BeanUtilsBean {
    // ------------------------------------------------------ Private Class Variables

    /**
     * Contains <code>BeanUtilsBean</code> instances indexed by context classloader.
     */
    private static final ContextClassLoaderLocal beansByClassLoader = new ContextClassLoaderLocal() {
        // Creates the default instance used when the context classloader is unavailable
        protected Object initialValue() {
            return new BeanUtilsBean();
        }
    };

    /**
     * Gets the instance which provides the functionality for {@link BeanUtils}. This is a pseudo-singleton - an single instance is provided per
     * (thread) context classloader. This mechanism provides isolation for web apps deployed in the same container.
     */
    public synchronized static BeanUtilsBean getInstance2() {
        return (BeanUtilsBean) beansByClassLoader.get();
    }

    /**
     * Sets the instance which provides the functionality for {@link BeanUtils}. This is a pseudo-singleton - an single instance is provided per
     * (thread) context classloader. This mechanism provides isolation for web apps deployed in the same container.
     */
    public synchronized static void setInstance(BeanUtilsBean newInstance) {
        beansByClassLoader.set(newInstance);
    }

    /**
     * Logging for this instance
     */
    private Log log = LogFactory.getLog(BeanUtils.class);

    /**
     * 复写父类方法，只拷贝orig中不为null的属性
     */
    public void copyProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {

        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }
        if (log.isDebugEnabled()) {
            //log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
        }

        // Copy the properties, converting as necessary
        if (orig instanceof DynaBean) {
            DynaProperty origDescriptors[] = ((DynaBean) orig).getDynaClass().getDynaProperties();
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if (getPropertyUtils().isWriteable(dest, name)) {
                    Object value = ((DynaBean) orig).get(name);
                    if (value != null)
                        copyProperty(dest, name, value);
                }
            }
        } else if (orig instanceof Map) {
            Iterator names = ((Map) orig).keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                if (getPropertyUtils().isWriteable(dest, name)) {
                    Object value = ((Map) orig).get(name);
                    if (value != null)
                        copyProperty(dest, name, value);
                }
            }
        } else /* if (orig is a standard JavaBean) */{
            PropertyDescriptor origDescriptors[] = getPropertyUtils().getPropertyDescriptors(orig);
            for (int i = 0; i < origDescriptors.length; i++) {
                String name = origDescriptors[i].getName();
                if ("class".equals(name)) {
                    continue; // No point in trying to set an object's class
                }
                if (getPropertyUtils().isReadable(orig, name) && getPropertyUtils().isWriteable(dest, name)) {
                    try {
                        Object value = getPropertyUtils().getSimpleProperty(orig, name);
                        if (value != null)
                            copyProperty(dest, name, value);
                    } catch (NoSuchMethodException e) {
                        ; // Should not happen
                    }
                }
            }
        }

    }
}
