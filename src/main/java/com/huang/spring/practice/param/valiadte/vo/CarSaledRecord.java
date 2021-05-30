package com.huang.spring.practice.param.valiadte.vo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/30
 */
public class CarSaledRecord {

    private Map<@NotNull String, List<@Valid Car>> salerAndSaledCarMap = new HashMap<>();

    public CarSaledRecord(Map<@NotNull String, List<Car>> salerAndSaledCarMap) {
        this.salerAndSaledCarMap = salerAndSaledCarMap;
    }
}
