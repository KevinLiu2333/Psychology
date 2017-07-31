/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.classify;

/**
* 停用词处理器
* @author phinecos 
* 
*/
public class StopWordsHandler 
{
	private static String stopWordsList[] ={"的", "我们","要","自己","之","将","“","”","，","（","）","后","应","到","某","后","个","是","位","新","一","两","在","中","或","有","更","好",""};//常用停用词
	public static boolean IsStopWord(String word)
	{
		for(int i=0;i<stopWordsList.length;++i)
		{
			if(word.equalsIgnoreCase(stopWordsList[i]))
				return true;
		}
		return false;
	}
}
