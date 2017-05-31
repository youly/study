package com.lastww.study.web.controller;

import com.lastww.study.web.AbstractTestCase;
import com.lastww.study.web.bootstrap.App;
import com.lastww.study.web.dao.Token;
import com.lastww.study.web.dao.interfaces.TokenDao;
import com.lastww.study.web.service.TokenService;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * Created by Youly<hzliuweiwei3@corp.netease.com> on 2017/5/24.
 */
public class HomeControllerTest extends AbstractTestCase {

    @Resource
    private TokenService tokenService;

    @Mocked
    private TokenDao tokenDao;

    @Test
    public void test() {
        new Expectations() {
            {
                tokenDao.insert((Token) any);
                result = 1;
                times = 1;
            }
        };
        ReflectionTestUtils.setField(tokenService, "tokenDao", tokenDao);
        tokenService.insert();
    }
s
    @Test
    public void test1() {
        MockUp<TokenDao> tokenDaoMockUp = new MockUp<TokenDao>() {
            @Mock
            Integer insert(Token token) {
                return 1;
            }
        };
        tokenService.insert();

    }
}