package com.kevin.springboot.redis.LRU;

/**
 * https://zhuanlan.zhihu.com/p/34133067
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/15
 * Time: 16:58
 */
public class DLinkedNode {
    String key;
    int value;
    DLinkedNode pre;
    DLinkedNode post;
}
