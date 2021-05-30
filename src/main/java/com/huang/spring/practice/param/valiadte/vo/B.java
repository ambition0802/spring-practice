package com.huang.spring.practice.param.valiadte.vo;

import javax.validation.constraints.NotNull;

/**
 * @author  yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/30
 */
public class B {

    @NotNull
    private A a;

    public void setA(A a) {
        this.a = a;
    }
}
