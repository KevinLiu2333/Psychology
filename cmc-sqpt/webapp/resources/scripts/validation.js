

//***************初始化处理**********************/
	function validateMobile(e,flag){
		if(flag==1&&e.value==""){
			e.isValid=true;
		}else{
			if (e.isValid) {
	            if (isMobile(e.value) == false) {
	                e.errorText = "请输入正确的手机号码格式";
	                e.isValid = false;
	            }
	        }
		}
		}
	function validateTelphone(e,flag){
		if(flag==1&&e.value==""){
			e.isValid=true;
		}else{
			if (e.isValid) {
		        if (isTelphone(e.value) == false) {
		            e.errorText = "请输入正确的电话号码格式";
		            e.isValid = false;
		        }
		    }
		}
	}
	function validateCardNo(e,flag){
		if(flag==1&&e.value==""){
			e.isValid=true;
		}else{
			if (e.isValid) {
				if (isCardNo(e.value) == false) {
	                e.errorText = "身份证输入有误！";
	                e.isValid = false;
	            }
		    }
		}
	}
	function validatePostcode(e,flag){
		if(flag==1&&e.value==""){
			e.isValid=true;
		}else{
			if (e.isValid) {
	            if (isPostcode(e.value) == false) {
	                e.errorText = "请输入正确的邮编格式";
	                e.isValid = false;
	            }
			}
		}
	}
	function validatePassWord(e){
		if (e.isValid) {
            if (isPassword(e.value) == false) {
                e.errorText = "请输入6位数字";
                e.isValid = false;
            }
        }
	}
	function validateAddress(e){
		if (e.isValid) {
            if (isAddress(e.value) == false) {
                e.errorText = "请以汉字开始！";
                e.isValid = false;
            }
        }
	}
	function isMobile(v){
		 var re =/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	    if (re.test(v)) return true;
	    return false;
	}
	function isTelphone(v){
		
		 var re = /^0\d{2,3}-?\d{7,8}$/;
	   if (re.test(v)) return true;
	   return false;
	}
	function isPostcode(v){
		 var re =/^[1-9][0-9]{5}$/;
	   if (re.test(v)) return true;
	   return false;
	}
	function isPassword(v){
		 var re =/^\d{6}$/;
	    if (re.test(v)) return true;
	    return false;
	}
	function isAddress(v){
		
		 var re = /[\u4e00-\u9fa5]/g;
	   if (re.test(v)) return true;
	   return false;
	}
	function isCardNo(v)  {  
	   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
	   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	   if (reg.test(v)) return true;
	   return false;
	}  
