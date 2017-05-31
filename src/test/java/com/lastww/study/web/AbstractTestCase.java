package com.lastww.study.web;

import com.lastww.study.web.bootstrap.App;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by Youly<hzliuweiwei3@corp.netease.com> on 2017/5/31.
 */
@SpringApplicationConfiguration(classes = {App.class})
public class AbstractTestCase extends AbstractTestNGSpringContextTests {
}