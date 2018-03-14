var chain={
    "default" : {
        "ps" : [
            //比默认配置仅仅多了这一行
            "com.kevin.nutzbook.mvc.LogTimeProcessor",
            "org.nutz.mvc.impl.processor.UpdateRequestAttributesProcessor",
            "org.nutz.mvc.impl.processor.EncodingProcessor",
            "org.nutz.mvc.impl.processor.ModuleProcessor",
            "!org.nutz.integration.shiro.NutShiroProcessor",
            "org.nutz.mvc.impl.processor.ActionFiltersProcessor",
            "org.nutz.mvc.impl.processor.AdaptorProcessor",
            "org.nutz.mvc.impl.processor.MethodInvokeProcessor",
            "org.nutz.mvc.impl.processor.ViewProcessor"
        ],
        "error" : 'org.nutz.mvc.impl.processor.FailProcessor'
    }
};