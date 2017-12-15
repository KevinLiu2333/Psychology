package com.kevin.springboot.utils.hash;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/10/30
 * Time: 13:33
 */
public class Hash {

    /**
     * 用jdk8的散列值优化函数
     *
     * @param key 关键字
     * @return key优化后的散列值
     */
    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);//key.hashCode()函数调用的是key键值类型自带的哈希函数,返回int型散列值.
    }

}
