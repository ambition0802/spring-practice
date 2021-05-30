package com.huang.spring.practice.param.valiadte.vo;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author  yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/29
 */
public class Car {

    @NotNull
    private String manufacturer;  // 制造商


    @NotNull
    @Size(min = 2, max = 14)
    private String licensePlate;  // 牌照

    @Min(2)
    private int seatCount;  // 座位数

    private Set<@NotEmpty String> parts = new HashSet<>();  // 零件

    @Valid
    private Driver driver;  // 驾驶员

    public Car(String manufacturer, String licencePlate, int seatCount) {
        this.manufacturer = manufacturer;
        this.licensePlate = licencePlate;
        this.seatCount = seatCount;
    }

    public void addPart(String part) {
        parts.add( part );
    }

    public void addDriver(Driver driver) {
        this.driver = driver;
    }

    //getters and setters ...
}