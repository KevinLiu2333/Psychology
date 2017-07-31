package com.wonders.sjfw.platform;

import java.util.Date;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.wonders.sjfw.platform.jk.FwProcessor;
import com.wonders.sjfw.platform.spu.KeySpu;
import com.wonders.sjfw.platform.spu.ParamSpu;
import com.wonders.sjfw.platform.spu.SpuFactory;

@IocBean
@Filters
public class FwCoreService {
	
	@Inject
	private KeySpu keySpu;

	@Inject
	private ParamSpu paramSpu;
	

    
    @At
    @Ok("raw")
    public String platformServices(String unitKey,String fwCode,Map<String,String> paramMap,String rsStyle,String rsStructure) {
    	//创建开始日期
        Date startDate = new Date();
        
        //1.验证unitKey参数
        String validResult = keySpu.validUnitKey(unitKey,FwConstants.FORMAT_JSON,startDate);
        if(!FwProcessor.VAILD_PARAM_PASS.equals(validResult)){
        	return validResult;
        }

        //2.验证fwCode参数
        validResult = keySpu.validFwCode(unitKey,FwConstants.FORMAT_JSON,fwCode,startDate);
        if(!FwProcessor.VAILD_PARAM_PASS.equals(validResult)){
        	return validResult;
        }
        //3.验证固定参数
        validResult = keySpu.validFwRSConfig(unitKey, FwConstants.FORMAT_JSON, startDate, rsStyle, rsStructure);
        if(!FwProcessor.VAILD_PARAM_PASS.equals(validResult)){
        	return validResult;
        }
        //4.自动授权
        String methodKey = keySpu.autoMethodKey(unitKey,fwCode,startDate);
        //5.组装参数
        FwParam fwParam = paramSpu.putInnerParam(paramMap, unitKey, methodKey,FwConstants.FORMAT_JSON,startDate);
        //6.调用服务的处理单元处理服务
        String result = SpuFactory.getInstance().produce(fwParam);
        //7.返回服务结果
        return result;
    }
    
   
}
