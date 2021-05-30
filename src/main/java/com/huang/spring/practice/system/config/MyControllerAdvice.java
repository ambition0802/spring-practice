package com.huang.spring.practice.system.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author  yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/25
 */
@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JSONObject exceptionHandler(Exception e) {
        // TODO

        e.printStackTrace();

        JSONObject ret = new JSONObject();
        ret.put("code", -1);
        ret.put("msg", e.getMessage());
        return ret;
    }
}
