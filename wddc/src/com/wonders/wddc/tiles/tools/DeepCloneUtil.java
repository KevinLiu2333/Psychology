package com.wonders.wddc.tiles.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class DeepCloneUtil {
    /** log4j日志，子类可以直接使用 */
    protected static Logger logger = Logger.getLogger(DeepCloneUtil.class);

    /**
     * 深度克隆
     * 
     * @param obj
     *            源对象
     * @return 克隆对象
     */
    public static Object deepClone(Object obj) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);

            return oi.readObject();
        } catch (Exception e) {
            logger.error(e);
        }
        return obj;
    }

}
