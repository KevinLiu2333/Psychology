function clear(e,reset){
		
			if(reset){
				if(e=="hjprovince"){
					clearComboboxValue("hjcity");
					clearComboboxValue("hjregion");
					clearComboboxValue("hjneighborhood");
					clearComboboxValue("hjresidcommittee");
				}else if(e=="hjcity"){
					clearComboboxValue("hjregion");
					clearComboboxValue("hjneighborhood");
					clearComboboxValue("hjresidcommittee");
				}else if(e=="hjregion"){
				
					clearComboboxValue("hjneighborhood");
					clearComboboxValue("hjresidcommittee");
				}else if(e=="hjneighborhood"){
					       
				
					
					clearComboboxValue("hjresidcommittee");
				}
			}
		}
function clearJZ(e,reset){
	
	if(reset){
		if(e=="jzprovince"){
			clearComboboxValue("jzcity");
			clearComboboxValue("jzregion");
			clearComboboxValue("jzneighborhood");
			clearComboboxValue("jzresidcommittee");
		}else if(e=="jzcity"){
			clearComboboxValue("jzregion");
			clearComboboxValue("jzneighborhood");
			clearComboboxValue("jzresidcommittee");
		}else if(e=="jzregion"){
		
			clearComboboxValue("jzneighborhood");
			clearComboboxValue("jzresidcommittee");
		}else if(e=="jzneighborhood"){
			       
		
			
			clearComboboxValue("jzresidcommittee");
		}
	}
}
		function jzjdEnable(e,reset){
			if(reset){
				if(e=="jzprovince"){
					mini.get('jzregion').disable(); 
					mini.get('jzneighborhood').disable(); 
					mini.get('jzresidcommittee').disable(); 
				
				}else if(e=="jzcity"){
					mini.get('jzregion').enable(); 
					mini.get('jzneighborhood').disable(); 
					mini.get('jzresidcommittee').disable(); 
				
					
				}else if(e=="jzregion"){
					mini.get('jzregion').enable(); 
					mini.get('jzneighborhood').enable(); 
					mini.get('jzresidcommittee').disable(); 
				}else if(e=="jzneighborhood"){
				
					mini.get('jzresidcommittee').enable();
					
				}
			}
			
		}

		function hjjdEnable(e,reset){
			if(reset){
				if(e=="hjprovince"){
					mini.get('hjregion').disable(); 
					mini.get('hjneighborhood').disable(); 
					mini.get('hjresidcommittee').disable(); 
				
				}else if(e=="hjcity"){
					mini.get('hjregion').enable(); 
					mini.get('hjneighborhood').disable(); 
					mini.get('hjresidcommittee').disable(); 
				
					
				}else if(e=="hjregion"){
					mini.get('hjregion').enable(); 
					mini.get('hjneighborhood').enable(); 
					mini.get('hjresidcommittee').disable(); 
				}else if(e=="hjneighborhood"){
				
					mini.get('hjresidcommittee').enable();
					
				}
			}
			
		}
		function loadHjprovince(reset){
			loadCombobox("hjprovince",baseurl+"organlevel=5&organtype=1");
		}
		function loadHjcity(reset){
			clear("hjprovince",reset);
			hjjdEnable("hjprovince",reset);
			loadRefCombobox("hjprovince","hjcity","4","1",reset);
		}
		function loadHjregion(reset){
			clear("hjcity",reset);
			hjjdEnable("hjcity",reset);
			loadRefCombobox("hjcity","hjregion","3","1",reset);
			
		}
		
		function loadHjneighborhood(reset){
			
			clear("hjregion",reset);
			hjjdEnable("hjregion",reset);
			loadRefCombobox("hjregion","hjneighborhood","2","1",reset);
			
		}
		
		function loadHjresidcommittee(reset){
			clear("hjneighborhood",reset);
			hjjdEnable("hjneighborhood",reset);
			loadRefCombobox("hjneighborhood","hjresidcommittee","1","1",reset);
		}
		function loadJzprovince(reset){
			loadCombobox("jzprovince",baseurl+"organlevel=5&organtype=1");
		}
		function loadJzcity(reset){
			clearJZ("jzprovince",reset);
			jzjdEnable("jzprovince",reset);
			loadRefCombobox("jzprovince","jzcity","4","1",reset);
		}
		function loadJzregion(reset){
			clearJZ("jzcity",reset);
			jzjdEnable("jzcity",reset);
			loadRefCombobox("jzcity","jzregion","3","1",reset);
			
		}
		
		function loadJzneighborhood(reset){
			
			clearJZ("jzregion",reset);
			jzjdEnable("jzregion",reset);
			loadRefCombobox("jzregion","jzneighborhood","2","1",reset);
			
		}
		
		function loadJzresidcommittee(reset){
			clearJZ("jzneighborhood",reset);
			jzjdEnable("jzneighborhood",reset);
			loadRefCombobox("jzneighborhood","jzresidcommittee","1","1",reset);
		}
		function loadRefCombobox(pname,objname,level,type,reset){
			
			var value = getComboboxValue(pname);
			if(value!=''){
				var url = getComboboxUrl(level,type,value);
			//	alert("url="+url);
				loadCombobox(objname,url);
			}
		}
		
		
		function getComboboxUrl(level,type,value){
		
			var url = baseurl +"organlevel="+level+"&organtype="+type;
			if(value){
				url += "&organparentId="+value;
			}
			return url;
		}
		
		function loadCombobox(objname,url){
		
			var obj = mini.get(objname);
			obj.setUrl(url);
			return setComboboxValue(objname);
		}
		///省市直接联动，市级一下默认为空不进行自动选择
		function setComboboxValue(objname){
			
			var obj = mini.get(objname);
			var value = obj.getValue();
			//alert('objname='+objname+'value='+value);
			/*if(editFlag=='add'){
				if((objname=="hjprovince"||objname=="hjcity")){
				  obj.select(0);
				  value = obj.getValue();
				}
			}*/
			return true;
		}
		
		function clearComboboxValue(objname){
			
			var obj = mini.get(objname);
			obj.setValue("");
		}
		
		function getComboboxValue(objname){
			
			var obj = mini.get(objname);
			var value = obj.getValue();
			return value;
		}
		function cleanLink(par,isprovince){
		    if(par=="hj"){
			  if(isprovince!=null){
				  var province =mini.get("hjprovince").getValue();
				  if(province!=null &&province=='310000'){
					  $("#shhjjd_show").show();
				  }else{
					  $("#shhjjd_show").hide();
				  }
			  }
			  mini.get("hjneighborhood").setValue('');
			  mini.get("hjresidcommittee").setValue('');
			  mini.get("bjdHJ").setValue('');
		    }else if(par=="jz"){
			  if(isprovince!=null){
				  var province =mini.get("jzprovince").getValue();
				  if(province!=null &&province=='310000'){
					  $("#shjzjd_show").show();
				  }else{
					  $("#shjzjd_show").hide();
				  }
			  }
			  mini.get("jzneighborhood").setValue('');
			  mini.get("jzresidcommittee").setValue('');
			  mini.get("bjdJZ").setValue('');
		    }
	   }	