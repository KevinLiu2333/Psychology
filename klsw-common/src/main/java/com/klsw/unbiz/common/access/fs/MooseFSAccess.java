/**
 *
 */
package com.klsw.unbiz.common.access.fs;

import com.klsw.unbiz.common.access.Access;
import com.klsw.unbiz.common.access.AccessDecorator;

/**
 * @author <a href="mailto:xuchen06@baidu.com">xuc</a>
 * @version create on 2014年7月25日 下午5:43:22
 */
public class MooseFSAccess extends AccessDecorator {

    public MooseFSAccess(Access access) {
        super(access, "MooseFS");
    }

}
