package com.huang.spring.practice.param.valiadte.vo;

import javax.validation.constraints.NotEmpty;

/**
 * @author  yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/30
 */
public class Driver {

    @NotEmpty
    private String driveLicense;

    public Driver(String driveLicense) {
        this.driveLicense = driveLicense;
    }

}
