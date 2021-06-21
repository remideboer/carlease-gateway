package com.monolithical.gateway.api.contracts.bodies;

public class CustomerContractData {
    private LeaseRateResponse leaseRateResponse;
    private Customer customer;

    public CustomerContractData() {
    }

    public CustomerContractData(Customer customer, LeaseRateResponse leaseRateResponse) {
        this.leaseRateResponse = leaseRateResponse;
        this.customer = customer;
    }

    public LeaseRateResponse getLeaseRateResponse() {
        return leaseRateResponse;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setLeaseRateResponse(LeaseRateResponse leaseRateResponse) {
        this.leaseRateResponse = leaseRateResponse;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
