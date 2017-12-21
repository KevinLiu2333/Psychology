package com.wonders.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * List和Map工具类。
 * <p>
 * 在前台中使用的比较多，比如AJAX和TagLib。
 * 
 * @since 1.1.0
 */

public class ListMapUtil {

    /**
     * 将map结构转变成js的array结构。
     * <P>
     * 例如：[{key:'1001',value:'test1'},{key:'1002',value:'test2'},{key:'1003',value:'test3'}]
     * 
     * @param Map
     * @return js的array结构
     * 
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static String map2StrArray(Map map) throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        StringBuffer strMap = new StringBuffer();
        strMap.append("[");

        if (map != null && map.size() > 0) {
            Iterator iter = map.keySet().iterator();
            int i = 0;

            while (iter.hasNext()) {
                Object key = iter.next();
                Object value = map.get(key);
                if (value == null) {
                    value = new String("");
                }

                if (i > 0) {
                    strMap.append(",");
                } else {
                    i = 1;
                }

                strMap.append("{key:'").append(key).append("',value:'").append(value).append("'}");
            }
        }
        strMap.append("]");

        return strMap.toString();
    }

    /**
     * 将list结构转变成js的array结构，要求list中包含的是bo。
     * <P>
     * 例如：[{id:'1001',name:'test1'},{id:'1002',name:'test2'},{id:'1003',name:'test3'}]。
     * 
     * @param list
     *            List结构
     * @return js的array结构
     * 
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static String list2StrArray(List list) throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        StringBuffer strMap = new StringBuffer();

        strMap.append("[");
        if (list != null && list.size() > 0) {
            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                Object obj = list.get(i);

                if (i != listSize - 1)
                    strMap.append(object2StrMap(obj)).append(",");
                else
                    strMap.append(object2StrMap(obj));
            }
        }
        strMap.append("]");

        return strMap.toString();
    }

    /**
     * 将object的结构转变成js的map结构 例如：{id:'1001',name:'test'}。
     * 
     * @param obj
     *            任一对象
     * @return js的map结构
     * 
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static String object2StrMap(Object obj) throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        if (obj == null)
            return "{}";

        StringBuffer strMap = new StringBuffer();

        // 获得bo的属性字段
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        // 取出mode的属性值
        strMap.append("{");
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String fieldValue = BeanUtils.getProperty(obj, fieldName);
            if (fieldValue == null)
                fieldValue = "";

            if (i != fields.length - 1)
                strMap.append(fieldName + ":'" + fieldValue + "',");
            else
                strMap.append(fieldName + ":'" + fieldValue + "'");
        }
        strMap.append("}");

        return strMap.toString();
    }

    /**
     * 根据List中对象的key和value属性，转换成Map。
     * 
     * @param list
     *            List对象
     * @param getKey
     *            list中对象的key属性取得方法
     * @param getValue
     *            list中对象的value属性取得方法
     * @return 转换后的Map
     * @throws Exception
     */
    public static Map list2Map(List list, String getKey, String getValue) throws Exception {
        if (list == null)
            return null;

        Map result = new LinkedHashMap();

        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            // 反射获得list中bo的方法
            Method aGetKey = obj.getClass().getMethod(getKey, null);
            Method aGetValue = obj.getClass().getMethod(getValue, null);
            // 反射获得list中bo的值
            Object objKey = aGetKey.invoke(obj, null);
            Object objValue = aGetValue.invoke(obj, null);

            if ((objKey != null) & (objValue != null)) {
                result.put(objKey.toString(), objValue.toString());
            }
        }

        return result;
    }

    /**
     * 将数组转变成list
     * 
     * @param objects
     * @return
     */
    public static List array2List(Object[] objects) {
        List list = null;

        if (objects != null && objects.length > 0) {
            list = new ArrayList();
            for (int i = 0; i < objects.length; i++)
                list.add(objects[i]);

        }

        return list;
    }

    /**
     * 通过id号找到list。
     * 
     * @param methodName
     *            方法名
     * @param id
     *            号码
     * @return 列表
     */
    public static List getSubListFromListById(List list, String methodName, Object id) {
        List rtnList = new ArrayList();

        if (id != null) {
            if (list.size() > 0) {
                // 获得类的方法对象
                Class clazz = list.get(0).getClass();
                Method meth = getMethodFromClassByMethodName(clazz, methodName);

                // 从list中找到list
                if (meth != null) {
                    for (int i = 0; i < list.size(); i++) {
                        try {
                            Object rtnObj = meth.invoke(list.get(i), null);

                            if (id.equals(rtnObj)) {
                                rtnList.add(list.get(i));
                            }
                        } catch (Throwable e) {
                            System.err.println(e);
                        }
                    }
                }
            }
        }

        return rtnList;
    }

    /**
     * 通过id号找到object。
     * 
     * @param methodName
     *            方法名
     * @param id
     *            号码
     * @return 对象
     */
    public static Object getObjectFromListById(List list, String methodName, Object id) {
        Object rtnObject = null;

        if (id != null) {
            if (list.size() > 0) {
                // 获得类的方法对象
                Class cls = list.get(0).getClass();
                Method meth = getMethodFromClassByMethodName(cls, methodName);

                // 从list中找到Object
                if (meth != null) {
                    for (int i = 0; i < list.size(); i++) {
                        try {
                            Object obj = meth.invoke(list.get(i), null);

                            if (id.equals(obj)) {
                                rtnObject = list.get(i);
                                break;
                            }
                        } catch (Throwable e) {
                            System.err.println(e);
                        }
                    }
                }
            }
        }

        return rtnObject;
    }

    /**
     * 从一个list中减去另外一个list
     * 
     * @param fromList
     *            减数
     * @param minuendList
     *            被减数
     * @return list
     */
    public static List listMinus(Collection fromList, Collection minuendList) {
        if (fromList != null && minuendList != null)
            for (Iterator iter = minuendList.iterator(); iter.hasNext();) {
                Object obj = iter.next();

                if (fromList.contains(obj)) {
                    fromList.remove(obj);
                }
            }

        return new ArrayList(fromList);
    }

    /**
     * 将String类型的数组转变成List
     * 
     * @param strArray
     *            String类型的数组
     * @return list
     */
    public static List stringArray2List(String[] strArray) {
        List list = null;

        if (strArray != null && strArray.length > 0) {
            list = new ArrayList();

            for (int i = 0; i < strArray.length; i++)
                list.add(strArray[i]);
        }

        return list;

    }

    /**
     * 通过方法的名称从类中获得方法对象。
     * 
     * @param clazz
     *            类
     * @param methodName
     *            方法名
     * @return Method 方法对象
     */
    private static Method getMethodFromClassByMethodName(Class clazz, String methodName) {
        Method meth = null;

        Method methlist[] = clazz.getDeclaredMethods();
        for (int i = 0; i < methlist.length; i++) {
            if (methodName.equalsIgnoreCase(methlist[i].getName())) {
                meth = methlist[i];
                break;
            }
        }

        return meth;
    }

    /**
     * 将两个Collection对象合并（取交集）
     * 
     * 注：将拷贝对象中的与源中不重复的拷贝到源中
     * 
     * @param cs
     *            源
     * @param cf
     *            拷贝对象
     */
    public static void mergeList(Collection cs, Collection cf) {
        if (cs != null && cf != null)
            for (Iterator iter = cf.iterator(); iter.hasNext();) {
                Object obj = iter.next();
                if (!cs.contains(obj)) {
                    cs.add(obj);
                }
            }
    }
}
