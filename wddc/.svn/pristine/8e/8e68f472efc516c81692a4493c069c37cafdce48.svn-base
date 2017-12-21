package com.wonders.sjfw.platform.spu;

import org.nutz.mvc.Mvcs;

import com.wonders.sjfw.platform.FwParam;
import com.wonders.sjfw.platform.jk.FwProcessor;
/**
 * 服务处理单元工厂.
 *
 */
public class SpuFactory {
	
	 /**
	  * 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载  
	  */
    private static SpuFactory instance = null;  
  
    /**
     * 私有构造方法，防止被实例化
     */
    private SpuFactory() {  
    }  
  
    /**
     * 静态工程方法，创建实例  
     * @return
     */
    public static SpuFactory getInstance() {  
        if (instance == null) {  
            instance = new SpuFactory();  
        }  
        return instance;  
    }  
  
    /**
     * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
     * @return
     */
    public Object readResolve() {  
        return instance;  
    }  
    
    /**
     * 服务返回数据处理.
     * @param fwTypeCode 服务类型代码
     * @param unitKey unitKey
     * @param methodKey methodKey
     * @param fwInfoId 服务主键
     * @return
     */
    public String produce(FwParam spuParam){
    	//返回数据结果
    	String result = null;
        try{
        	//获取执行处理的类
        	Class<?> processorClass = Class.forName("com.wonders.sjfw.platform.processor."+spuParam.fwInfo.getFwTypeCode()+"Processor");
        	//根据类获取执行处理的bean
        	FwProcessor processorInstance = (FwProcessor)Mvcs.getIoc().get(processorClass);
        	//获取参数验证的结果
        	String paramResult = processorInstance.validParam(spuParam);
        	//如果参数验证通过
        	if(FwProcessor.VAILD_PARAM_PASS.equals(paramResult)){
        		//数据处理结果
        		result = processorInstance.opResult(spuParam);
        	}else{
        		result = paramResult;
        	}
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 服务返回数据处理.
     * @param fwTypeCode 服务类型代码
     * @param unitKey unitKey
     * @param methodKey methodKey
     * @param fwInfoId 服务主键
     * @return
     */
    public String produceExample(FwParam spuParam){
    	//返回数据结果
    	String result = null;
        try{
        	//获取执行处理的类
        	Class<?> processorClass = Class.forName("com.wonders.sjfw.platform.processor."+spuParam.fwInfo.getFwTypeCode()+"Processor");
        	//根据类获取执行处理的bean
        	FwProcessor processorInstance = (FwProcessor)Mvcs.getIoc().get(processorClass);
        	//数据处理结果
        	result = processorInstance.opExample(spuParam);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
}
