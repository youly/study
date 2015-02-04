package com.lastww.study.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by liuweiwei on 15-2-4.
 * 测试spring加载bean的顺序
 */
public class A implements InitializingBean {

    //beans.xml文件配置：default-autowire=byName，spring会在bean实例化后自动注入
    private B b;
    private String name; // = b.funb();

    public void setB(B b) {
        System.out.println("A.setB initialed");
        this.b = b;
    }

    public A() {
        System.out.println("A initialed");
    }

    public void init() {
        System.out.println("init");
        this.name = b.funb();
    }

    @Override
    public String toString() {
        return super.toString() + this.name;
    }

    public void afterPropertiesSet() throws Exception {

        //其实放在这里也可以

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

    三、依赖管理
    先实例化bean，并记录bean的依赖。当实例化到bean的依赖时，通过setter方法注入
     */

}

