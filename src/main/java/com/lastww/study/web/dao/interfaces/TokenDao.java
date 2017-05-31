package com.lastww.study.web.dao.interfaces;

import com.lastww.study.web.dao.Token;

/**
 * Created by Youly<hzliuweiwei3@corp.netease.com> on 2017/5/24.
 */
public interface TokenDao {

    Integer insert(Token token);

    Integer updateByPrimaryKey(Token token);
}