package com.huang.spring.practice.param.valiadte.vo;

import javax.validation.constraints.NotNull;

/**
 * @author  yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/30
 */
public class A {

    @NotNull
    private B b;

   public void setB(B b) {
       this.b = b;
   }
}
