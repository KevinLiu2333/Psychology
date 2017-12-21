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
	$.get($("#js_ctx").val()+"/wddc/tiles/js/dic.json",function(data){ 
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
	});}
	if(options.subselect!=null&&options.subselect.length>0){
		var sub = options.subselect[0];
		$(this).change(function(){
			var val = $(this).val();
			if(options.subselect.length>1){
				$('#'+sub.id).jsondic({
					dicid:options.dicid+"#"+val,
					initvalue:sub.initvalue,
					subselect : options.subselect.slice(1)
				});
			}else{
				$('#'+sub.id).jsondic({
					dicid:options.dicid+"#"+val,
					initvalue:sub.initvalue
				});
			}
		});
		$(this).change();
	}
};
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
$.fn.dicselect = function(options){
            if(options.initvalue!=null&&options.initvalue!=''){
                this.append("<option>"+options.initvalue+"</option>");  
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
$.fn.dicvalue = function(options){
    var	dic = eval('('+options.dic+")");
    if(options.prop != undefined && options.prop!=null){
    	this.attr(options.prop,dic[options.value]);
    }else{
    	this.html(dic[options.value]);
    }
};

$.fn.dicajaxselect = function(options){
    var select = this;
    $.get(options.url,function(data){
    	select.find("option").remove();
        if(options.initvalue!=null&&options.initvalue!=''){
        	if(options.initoptvalue!=null&&options.initoptvalue!=''){
        		select.append("<option value='"+options.initoptvalue+"'>"+options.initvalue+"</option>");  
        	}else{
        		select.append("<option>"+options.initvalue+"</option>");  
        	}
        }
    	var dic = eval('('+data+')');
        for(key in dic){
            if(key == options.defaultvalue){
            	select.append("<option value='"+key+"' selected>"+dic[key]+"</option>");
            }else{
            	select.append("<option value='"+key+"'>"+dic[key]+"</option>");
            }
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