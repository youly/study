package com.lastww.study.web.controller;

import com.lastww.study.web.dao.Token;
import com.lastww.study.web.dao.interfaces.TokenDao;
import com.lastww.study.web.service.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Youly<hzliuweiwei3@corp.netease.com> on 2017/5/24.
 */
@Controller
public class HomeController {

    @Resource
    private TokenService tokenService;

    @RequestMapping("/")
    @ResponseBody
    public Object index() {
        tokenService.insert();
        return "test";
    }
}