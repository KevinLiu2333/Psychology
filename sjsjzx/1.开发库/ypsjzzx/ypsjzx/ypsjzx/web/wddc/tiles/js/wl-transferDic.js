/*
* @Author: wulong
* @Date:   2017-05-26 16:34:53
* @Last Modified by:   wulong
* @Last Modified time: 2017-05-26 16:35:47
*/


// 字典转换方法  参数为：json格式的根数组，待转字典
    function transferDic(baseArray,dic){
        var baseDic = {};
        for(var i = 0;i<baseArray.length;i++){
            baseDic[baseArray[i]] = 0;
        }
        // 对比插入
        var count = [];      //数字项
        var countValue = []; //对应的String项
        var keyCount = [];
        //获取字典的key并存入数组中
        for(var key in dic){
        	keyCount.push(key);
        }
    	var countLength = keyCount.length;
    	for(var i =0;i < countLength;i++){
            if( !isNaN(parseInt(dic[keyCount[i]][0])) ){    //判断第0项的value是否为数字count  是数字则进if语句内
                for(var j in dic[keyCount[i]]){
                    count.push(dic[keyCount[i]][j]); //把传入dic中的数字项赋值给count数组
                    countValue.push(dic[keyCount[i+1]][j]);
                }
                break;
            }else{
            	for(var j in dic[keyCount[i]]){
                    count.push(dic[keyCount[i+1]][j]); //把传入dic中的数字项赋值给count数组
                    countValue.push(dic[keyCount[i]][j]);
            	}
            	break;
            }
    	}
    	
    	for(var baseKey in baseDic){
    		for(var i = 0;i<countValue.length;i++){
	            if(JSON.stringify(countValue[i]) == JSON.stringify(baseKey)){
	                baseDic[baseKey] = count[i];    // 插入
	            }
    		}
        } 
        
        //返回插入好数据的根字典
        return baseDic;
    }