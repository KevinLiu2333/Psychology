/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.util;

/**
 * The Class ReflectHelper.
 */
public class ReflectHelper {
	
	/**
	 * Class for name.
	 * 
	 * @param name the name
	 * 
	 * @return the class
	 * 
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static Class classForName(String name) throws ClassNotFoundException {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if ( contextClassLoader != null ) {
				return contextClassLoader.loadClass(name);
			}
		}
		catch ( Throwable t ) {
		}
		return Class.forName( name );
	}

	/**
	 * Class for name.
	 * 
	 * @param name the name
	 * @param caller the caller
	 * 
	 * @return the class
	 * 
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static Class classForName(String name, Class caller) throws ClassNotFoundException {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if ( contextClassLoader != null ) {
				return contextClassLoader.loadClass( name );
			}
		}
		catch ( Throwable e ) {
		}
		return Class.forName( name, true, caller.getClassLoader() );
	}
}
