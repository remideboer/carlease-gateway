package com.monolithical.gateway.api.contracts.bodies;

import java.math.BigDecimal;

/**
 * Model to represent car data
 */
public class Car {

    private Long id;
    private String make;
    private String model;
    private String version;
    private int doorCount;
    private BigDecimal co2Emission;
    private Long grossPrice;
    private Long nettPrice;

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }

    public int getDoorCount() {
        return doorCount;
    }

    public BigDecimal getCo2Emission() {
        return co2Emission;
    }

    public Long getGrossPrice() {
        return grossPrice;
    }

    public Long getNettPrice() {
        return nettPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDoorCount(int doorCount) {
        this.doorCount = doorCount;
    }

    public void setCo2Emission(BigDecimal co2Emission) {
        this.co2Emission = co2Emission;
    }

    public void setGrossPrice(Long grossPrice) {
        this.grossPrice = grossPrice;
    }

    public void setNettPrice(Long nettPrice) {
        this.nettPrice = nettPrice;
    }
}
