/**
 * 使用dic.json做字典：
 * dicid 字典编号
 * initvalue 初始化选项（请选择这项在这配）
 * initoptvalue 初始化选项的值 （请选择对应的value）
 * defaultvalue 默认值 即默认选择项的字典id
 * subdisable （true or false）主选择框处于请选择状态时子选择框为disable状态值
 * subdisplay （true or false）主选择框处于请选择状态时子选择框为不显示状态
 * subselect 子选择框 使用json格式字符串编写 json中包括 id(子选择框id) defaultvalue  initvalue
 */
var $dicjson;
$.fn.jsondic = function(options){
	var select = this;
	if($dicjson != undefined && $dicjson!=null){
		select.find("option").remove();
		var dic = $dicjson[options.dicid];
		if(options.initvalue!=null&&options.initvalue!=''){
			if(options.initoptvalue!=null&&options.initoptvalue!=''){
        		select.append("<option value='"+options.initoptvalue+"'>"+options.initvalue+"</option>");  
        	}else{
        		select.append("<option>"+options.initvalue+"</option>");  
        	}
        }
		for(key in dic){
            if(key == options.defaultvalue){
            	select.append("<option value='"+key+"' selected>"+dic[key]+"</option>");
            }else{
            	select.append("<option value='"+key+"'>"+dic[key]+"</option>");
            }
        }
	}else{
		$.ajax({
			type: "GET",
			url: $("#js_ctx").val()+"/wddc/tiles/js/dic.json",
			dataType: "text",  
			success: function(data){
				select.find("option").remove();
				data = eval('('+data+")");
				$dicjson = data;
				var dic = data[options.dicid];
				if(options.initvalue!=null&&options.initvalue!=''){
					if(options.initoptvalue!=null&&options.initoptvalue!=''){
		        		select.append("<option value='"+options.initoptvalue+"'>"+options.initvalue+"</option>");  
		        	}else{
		        		select.append("<option>"+options.initvalue+"</option>");  
		        	} 
		        }
				for(key in dic){
		            if(key == options.defaultvalue){
		            	select.append("<option value='"+key+"' selected>"+dic[key]+"</option>");
		            }else{
		            	select.append("<option value='"+key+"'>"+dic[key]+"</option>");
		            }
		        }
			}
		});
	}
	if(options.subselect!=null&&options.subselect.length>0){
		var subdisable = options.subdisable;
		var subdisplay = options.subdisplay;
		var sub = options.subselect[0];
		$(this).change(function(){
			var val = $(this).val();
			if(options.subselect.length>1){
				$('#'+sub.id).jsondic({
					dicid:options.dicid+"#"+val,
					initvalue:sub.initvalue,
					initoptvalue:options.initoptvalue,
					defaultvalue:sub.defaultvalue,
					subselect : options.subselect.slice(1),
					subdisable: options.subdisable,
					subdisplay: options.subdisplay
				});
			}else{
				$('#'+sub.id).jsondic({
					dicid:options.dicid+"#"+val,
					defaultvalue:sub.defaultvalue,
					initvalue:sub.initvalue,
					initoptvalue:options.initoptvalue
				});
			}
			if(subdisable && ($(this).val()==options.initvalue || $(this).val()==options.initoptvalue)){
				$('#'+sub.id).attr("disabled",true);
			}
			if(subdisable && !($(this).val()==options.initvalue || $(this).val()==options.initoptvalue)){
				$('#'+sub.id).attr("disabled",false);
			}
			if(subdisplay && ($(this).val()==options.initvalue || $(this).val()==options.initoptvalue)){
				$('#'+sub.id).hide();
			}
			if(subdisplay && !($(this).val()==options.initvalue || $(this).val()==options.initoptvalue)){
				$('#'+sub.id).show();
			}
		});
		$(this).change();
		if(options.defaultvalue != undefined && options.defaultvalue!=''){
			var val = options.defaultvalue;
			if(options.subselect.length>1){
				$('#'+sub.id).jsondic({
					dicid:options.dicid+"#"+val,
					initvalue:sub.initvalue,
					defaultvalue:sub.defaultvalue,
					subselect : options.subselect.slice(1),
					subdisable: options.subdisable,
					subdisplay: options.subdisplay
				});
			}else{
				$('#'+sub.id).jsondic({
					dicid:options.dicid+"#"+val,
					defaultvalue:sub.defaultvalue,
					initvalue:sub.initvalue
				});
			}
		}
		if(subdisable && (options.defaultvalue==options.initvalue || options.defaultvalue==options.initoptvalue)){
			$('#'+sub.id).attr("disabled",true);
		}
		if(subdisable && !(options.defaultvalue==options.initvalue || options.defaultvalue==options.initoptvalue)){
			$('#'+sub.id).attr("disabled",false);
		}
		if(subdisplay && (options.defaultvalue==options.initvalue || options.defaultvalue==options.initoptvalue)){
			$('#'+sub.id).hide();
		}
		if(subdisplay && !(options.defaultvalue==options.initvalue || options.defaultvalue==options.initoptvalue)){
			$('#'+sub.id).show();
		}
	}
};
/**
 * 根据dic.json将字典翻译：
 * dicid：字典id
 * value：翻译的字典值
 * prop：翻译的目标属性，默认html，例如需要将翻译中翻译到input输入框中 这值赋值为value 
 */
$.fn.jsondicvalue = function(options){
	var curtag=this;
	if($dicjson != undefined && $dicjson!=null){
		var dic = $dicjson;
		if(options.prop != undefined && options.prop!=null){
			curtag.attr(options.prop,dic[options.dicid][options.value]);
		}else{
			curtag.html(dic[options.dicid][options.value]);
		}
	}else{
		$.get($("#js_ctx").val()+"/wddc/tiles/js/dic.json",function(data){ 
			data = eval('('+data+")");
			$dicjson = data;
			var dic = $dicjson;
			if(options.prop != undefined && options.prop!=null){
				curtag.attr(options.prop,dic[options.dicid][options.value]);
			}else{
				curtag.html(dic[options.dicid][options.value]);
			}
		});
	}
};
/**
 * 普通的字典选择框:
 * initvalue : 初始化选项 （请选择）
 * initval : 初始化选项的值 （请选择对应的值）
 * dic : 字典 json格式的字典 如{'1':'男','2':'女'}
 */
$.fn.dicselect = function(options){
            if(options.initvalue!=null&&options.initvalue!=''){
            	if(options.initval!=null&&options.initval!=''){
            		this.append("<option value='"+options.initval+"'>"+options.initvalue+"</option>");  
            	}else{
            		this.append("<option value=''>"+options.initvalue+"</option>");  
            	}
                
            }
            var dic;
            if(options.dic!=null&&options.dic!=''){
            	dic = eval('('+options.dic+")");
            }
            if(options.json!=null&&options.json!=''){
            	dic = options.json;
            }
            for(key in dic){
                if(key == options.defaultvalue){
                     this.append("<option value='"+key+"' selected>"+dic[key]+"</option>");
                }else{
                    this.append("<option value='"+key+"'>"+dic[key]+"</option>");
                }
            }
};
/**
 * 普通字典翻译：
 * dic : 字典
 * value : 字典值
 * prop：翻译的目标属性，默认html，例如需要将翻译中翻译到input输入框中 这值赋值为value 
 */
$.fn.dicvalue = function(options){
    var	dic = eval('('+options.dic+")");
    if(options.prop != undefined && options.prop!=null){
    	this.attr(options.prop,dic[options.value]);
    }else{
    	this.html(dic[options.value]);
    }
};
/**
 * ajax方式的字典选择框:
 * url  字典提供的url
 * initvalue 初始化选项（请选择这项在这配）
 * initoptvalue 初始化选项的值 （请选择对应的value）
 * defaultvalue 默认选中值
 * callback 回调函数callback(data,options) 在字典选择框设置好后执行 data为url提供的字典，options为当前选择框的参数
 * subselectjson 子选择框选项 json包括id（子选项的id）url defaultvalue initvalue
 */
$.fn.dicajaxselect = function(options){
    var select = this;
    $.get(options.url,function(data){
    	select.find("option").remove();
        if(options.initvalue!=null&&options.initvalue!=''){
        	if(options.initoptvalue!=null&&options.initoptvalue!=''){
        		select.append("<option value='"+options.initoptvalue+"'>"+options.initvalue+"</option>");  
        	}else{
        		select.append("<option value=''>"+options.initvalue+"</option>");  
        	}
        }
    	var dic = data;
        for(key in dic){
            if(key == options.defaultvalue){
            	select.append("<option value='"+key+"' selected>"+dic[key]+"</option>");
            }else{
            	select.append("<option value='"+key+"'>"+dic[key]+"</option>");
            }
        }
        if(typeof options.callback == "function"){
        	options.callback(data,options);
        }
    });
    var subselectjson = options.subselectjson;
    if(options.subselect!=null && options.subselect!=''){
    	subselectjson = eval('('+options.subselect+')');
    }
    if(subselectjson!=null&&subselectjson.length>0){
		var subselect = subselectjson[0];
		$(this).change(function(){
			var val = $(this).val();
			if(subselectjson.length>1){
				$('#'+subselect.id).dicajaxselect({
					url : subselect.url+val,
					defaultvalue : subselect.defaultvalue,
					initvalue : subselect.initvalue,
					subselectjson : subselectjson.slice(1)
				});
			}else{
				$('#'+subselect.id).dicajaxselect({
					url : subselect.url+val,
					defaultvalue : subselect.defaultvalue,
					initvalue : subselect.initvalue
				});
			}	
		});
		$(this).change();
	}
};
/**
 * ajax方式翻译字典
 * value 翻译的字典值
 * valueprop 翻译的字典值所在的参数值 与value不同时使用 例如<input val='1'> 此时翻译的字典值为val中的1是 此选项设置为val
 * url 字典提供的url
 * prop 翻译的目标属性，默认html，例如需要将翻译中翻译到input输入框中 这值赋值为value 
 */
$.fn.dicajaxvalue = function(options){
	var curtag = this;
	var dicvalue; 
	if(options.valueprop != undefined && options.valueprop !=null ){
		dicvalue = this.attr(options.valueprop);
	}else{
		dicvalue=options.value;
	}
	$.get(options.url,function(data){
		var dic = data ;
		if(options.prop != undefined && options.prop!=null){
			curtag.attr(options.prop,dic[dicvalue]);
	    }else{
	    	curtag.html(dic[dicvalue]);
	    }
	});
};