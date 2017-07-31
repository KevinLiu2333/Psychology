package com.wonders.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityUtils {
	/** 模板后缀 */
    public static final String TEMPLATE_POSTFIX_EXT = ".vm";
    // 应用环境目录
    private static String appPath = PropertyUtils.getProperty("app.path");
    /** 模板目录 */
    public static final String TEMPLATE_PATH = appPath + "tiles/vmtemplate";
    /**
     * 初始化velocity
     */
    static {
    	// 设置velocity的log
        Velocity.setProperty(Velocity.RUNTIME_LOG, TEMPLATE_PATH + "\\velocity.log");
        // 设置velocity的输入输出
        Velocity.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        Velocity.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        // 设置velocity的模板路径（必要）
        Velocity.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, TEMPLATE_PATH);
        // 初始化velocity引擎
        try {
            Velocity.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * @param templateVO
     * @param templateName
     * @param vmFileName
     * @return 文件的内�?
     * @throws Exception
     */
    public static String merge(Map<String, ?> velocityMap, String vmFileName) throws Exception {
    	// 取得velocity的上下文context
        VelocityContext context = new VelocityContext();
        // 取得velocity的模板
        Template template = Velocity.getTemplate(vmFileName + TEMPLATE_POSTFIX_EXT);
        // 输出�?
        StringWriter stringWriter = new StringWriter();
        // 把数据填入上下文
        Set<String> keySet = velocityMap.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            context.put(key, velocityMap.get(key));
        }
        if (template != null) {
            template.merge(context, stringWriter);
        }
        return stringWriter.toString();
    }
    /**
     * 生成模板字符�?
     * @param velocityMap 上下文map
     * @param vmFileName vm名称
     * @param fileName 文件名称
     * @throws Exception
     */
    public static void mergeToFile(Map<String, ?> velocityMap, String vmFileName,String fileName) throws Exception {
        // 取得velocity的上下文context
        VelocityContext context = new VelocityContext();
        // 取得velocity的模�?
        Template template = Velocity.getTemplate(vmFileName + TEMPLATE_POSTFIX_EXT);

        // 输出�?

        FileOutputStream fos = new FileOutputStream(fileName);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        // 把数据填入上下文
        Set<String> keySet = velocityMap.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            context.put(key, velocityMap.get(key));
        }
        if (template != null) {
            template.merge(context, osw);
        }
        osw.close();
        fos.close();
    }
    /**
     * 生成模板文件.
     * @param velocityMap 上下文map
     * @param vmFileName vm名称
     * @param fileName 文件名称
     * @throws Exception
     */
    public static void mergeToFile(Map<String, ?> velocityMap, String vmFileName,File targetFile) throws Exception {
        // 取得velocity的上下文context
        VelocityContext context = new VelocityContext();
        // 取得velocity的模�?
        Template template = Velocity.getTemplate(vmFileName + TEMPLATE_POSTFIX_EXT);
        // 输出�?
        FileOutputStream fos = new FileOutputStream(targetFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        // 把数据填入上下文
        Set<String> keySet = velocityMap.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            context.put(key, velocityMap.get(key));
        }
        if (template != null) {
            template.merge(context, osw);
        }
        osw.close();
        fos.close();
    }
}
