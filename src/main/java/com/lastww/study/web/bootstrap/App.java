package com.lastww.study.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Youly<hzliuweiwei3@corp.netease.com> on 2017/5/23.
 */
@ComponentScan(basePackages = {"com.lastww.study.web"})
@EnableAutoConfiguration
@ImportResource({"classpath:applicationContext.xml", "classpath:database.xml"})
public class App extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.setWebEnvironment(true);
        app.run(args);
    }
}