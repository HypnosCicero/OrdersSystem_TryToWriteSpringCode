package com.atguigu.myssm.io;

/**
 * 制作一个接口名为BeanFactory
 */
public interface BeanFactory {
    //主要目的：经过任何方法通过String类型的id获得到Object类型的对象
    Object getBean(String id);
}
