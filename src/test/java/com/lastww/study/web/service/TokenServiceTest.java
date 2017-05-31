package com.lastww.study.web.service;

import com.lastww.study.web.AbstractTestCase;
import com.lastww.study.web.dao.Token;
import com.lastww.study.web.dao.interfaces.TokenDao;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.Test;

/**
 * Created by Youly<hzliuweiwei3@corp.netease.com> on 2017/5/31.
 */
public class TokenServiceTest extends AbstractTestCase {

    @Tested
    private TokenService tokenService;

    @Injectable
    private TokenDao tokenDao;

    @Test
    public void test() {
        MockUp<TokenDao> tokenDaoMockUp = new MockUp<TokenDao>() {
            @Mock
            Integer insert(Token token) {
                return 1;
            }
        };
        ReflectionTestUtils.setField(tokenService, "tokenDao", tokenDaoMockUp.getMockInstance());
        tokenService.insert();
        tokenService.print();
    }
}