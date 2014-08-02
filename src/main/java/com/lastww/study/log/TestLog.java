package com.lastww.study.log;

import org.apache.log4j.Logger;

/**
 * Created by liuweiwei on 14-8-2.
 */
public class TestLog {

    static Logger log = Logger.getLogger(TestLog.class.getName());

    public static void main(String[] args) {
        log.error("error message");
        log.warn("warn message");
        log.info("info message");
        log.debug("debug message");
        log.trace("trace message");
    }
}
