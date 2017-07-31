package com.wondersgroup.sh.search.util;

public interface FileChangeListener {
	/** 
	 * 当文件改变过时回调这个方法。 
	 * @param 所要监视文件的文件名。支持classpath。
	 */
	public void fileChanged(String fileName);
}
