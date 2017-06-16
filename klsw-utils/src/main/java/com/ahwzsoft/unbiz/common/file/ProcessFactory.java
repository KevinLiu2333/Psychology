/**
 * 
 */
package com.ahwzsoft.unbiz.common.file;

import com.ahwzsoft.unbiz.common.Assert;
import com.ahwzsoft.unbiz.common.EnumUtil;

/**
 * @author <a href="mailto:xuchen06@baidu.com">xuc</a>
 * @version create on 2014年7月27日 下午5:48:14
 */
public abstract class ProcessFactory {

    public static FileProcessor create(FileType type) {
        return type.createProcessor();
    }

    public static FileProcessor create(String ext) {
        FileType type = EnumUtil.parseName(FileType.class, ext.toUpperCase());
        Assert.assertNotNull(type, "ext is illegal");

        return type.createProcessor();
    }

}
