package com.lastww.study.web.service;

import com.lastww.study.web.dao.Token;
import com.lastww.study.web.dao.interfaces.TokenDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Youly on 2017/5/24.
 */
@Service
public class TokenService {

    @Resource
    private TokenDao tokenDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Token insert() {
        Token token = new Token();
        int a = tokenDao.insert(token);
        return token;
    }

    public void print() {
        System.out.println("------------------test-----------------------");
    }
}
