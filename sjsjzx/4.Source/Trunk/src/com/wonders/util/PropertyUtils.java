package com.wonders.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.nutz.log.Log;
import org.nutz.log.Logs;

public class PropertyUtils {
	/** ��־ */
    private static Log log = Logs.get();

    /** �����ļ�ӵ���� */
    private static Properties p = new Properties();

    /**
     * ���ʼ��
     */
    static {

        InputStream is = PropertyUtils.class.getResourceAsStream("/application.properties");

        try {
            // ��ȡ���ò���
            p.load(is);

            // �ر�������
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }

    }

    /**
     * �����������ֵ
     * 
     * @param key
     *            ��������
     * @return ��������ֵ
     */
    public static String getProperty(String key) {
        return p.getProperty(key);
    }

    /**
     * �����������ֵ
     * 
     * @param key
     *            ��������
     * @param defaultValue
     *            Ĭ��ֵ
     * @return ��������ֵ
     */
    public static String getProperty(String key, String defaultValue) {
        return p.getProperty(key, defaultValue);
    }
}
