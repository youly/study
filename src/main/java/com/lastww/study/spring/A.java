package com.lastww.study.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by liuweiwei on 15-2-4.
 * 测试spring加载bean的顺序
 */
public class A implements InitializingBean {

    //如果beans.xml文件配置中配置了default-autowire=byName，spring会在bean实例化后通过setter方法注入,因此需提供settor方法
    private B b;
    private String name; // = b.funb();


    public void setB(B b) {
        System.out.println("A.setB initialed");
        this.b = b;
    }

    public A() {
        System.out.println("A initialed");
    }

/*
    public A(B b) {
        this.b = b;
        System.out.println("A initialed");
    }
*/

    /**
     * bean配置里面指定的init-method，bean实例化完后调用
     */
    public void init() {
        System.out.println("init");
        this.name = b.funb();
    }

    @Override
    public String toString() {
        return super.toString() + this.name;
    }

    /**
     * bean实例化完后调用，先于init
     */
    public void afterPropertiesSet() throws Exception {

        //this.name = b.funb();
        System.out.println("afterPropertiesSet");

    }

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/resources/beans.xml");
        A a = (A) context.getBean("a");
        System.out.println(a);
    }

    /*
    一、Spring装配Bean的过程
    1. 实例化;
    2. 设置属性值;
    3. 如果实现了BeanNameAware接口,调用setBeanName设置Bean的ID或者Name;
    4. 如果实现BeanFactoryAware接口,调用setBeanFactory 设置BeanFactory;
    5. 如果实现ApplicationContextAware,调用setApplicationContext设置ApplicationContext
    6. 调用BeanPostProcessor的预先初始化方法;
    7. 调用InitializingBean的afterPropertiesSet()方法;
    8. 调用定制init-method方法；
    9. 调用BeanPostProcessor的后初始化方法;

    二、Spring容器关闭过程
    1. 调用DisposableBean的destroy();
    2. 调用定制的destroy-method方法;

    三、类实例化与属性注入，按xml配置顺序实例化
    若有三个bean，A、B、C，A有个属性引用B，B有个属性引用C
    1、若通过constructor注入
       * 如果配置顺序是C、A、B，先实例化C，准备实例化A时发现A依赖B，因此先实例化B并把C注入到B，最后实例化A并把B注入到A。
       * 先解决依赖才能实例化
    2、若通过settor注入
       * 如果配置顺序是A、B、C，则先实例化A，然后实例化B，最后实例化C，然后把C注入到B，并把B注入到A
       * 如果配置顺序是C、A、B，则先实例化C，然后实例化A，最后实例化C，然后把C注入到B，然后把B注入到A。
       * 先实例化（调构造函数）再解决依赖
     */

}

