package com.monolithical.gateway.api.contracts.bodies;

import java.math.BigDecimal;

public class LeaseRateResponse {
    private LeaseContractData contractData;
    private BigDecimal leaseRate;

    public LeaseRateResponse() {
    }

    public void setContractData(LeaseContractData contractData) {
        this.contractData = contractData;
    }

    public LeaseContractData getContractData() {
        return contractData;
    }

    public BigDecimal getLeaseRate() {
        return leaseRate;
    }
}
