package com.monolithical.gateway.api.contracts.bodies;

import java.math.BigDecimal;

public class LeaseContractData {
    private int mileage;
    private int durationInMonths;
    private BigDecimal interestRatePercent;
    private Car car;

    public LeaseContractData() {
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }

    public BigDecimal getInterestRatePercent() {
        return interestRatePercent;
    }

    public void setInterestRatePercent(BigDecimal interestRatePercent) {
        this.interestRatePercent = interestRatePercent;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
