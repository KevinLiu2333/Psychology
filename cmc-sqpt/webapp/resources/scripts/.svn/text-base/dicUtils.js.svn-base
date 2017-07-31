/**
 * @desc:字典信息加载API
 * @author:xieguojun
 * @see 1.0
 */
if(typeof(__DIC_UTIL__) === 'undefined'){
	var __DIC_UTIL__="dic util 1.0";

	if(typeof(Namespace) ==='undefined'){
		function Namespace(ns){
		    if(typeof(ns)!="string")return;
		    var o =null;
		    var ni =null;
		    ns=ns.split(".");
		    for(var i=0,len=ns.length;i<len,ni=ns[i];i++){
		       try{o=(o?(o[ni]=o[ni]||{}):(eval(ni+"="+ni+"||{}")));}catch(e){o=eval(ni+"={}");};
		    }
		}
	}
	Namespace("Wssip.dic");
	Wssip.dic.load =function(catalog,codeType,success,failure){
		$.post(__WSSIP_DIC_URL__,{"catalog":catalog,"code":codeType},function(data,status){
			if(data.success==='true'||data.success===true){
				if(typeof(success)==='function')
					success(data.result,status);
				else
					;
			}else{
				if(typeof(failure)==='function')
					failure(data.result,status);
				else
					alert('ajax call fail,status['+status+']');
			}
		},'json')
		.fail(function(data,status){
			if(typeof(failure)==='function')
				failure(data,status);
			else
				alert('ajax call fail,status['+status+']');
		});

	};
};