package com.monolithical.gateway.api.contracts;

import com.monolithical.gateway.api.contracts.bodies.Customer;
import com.monolithical.gateway.api.contracts.bodies.CustomerContractData;
import com.monolithical.gateway.api.contracts.bodies.LeaseRateResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * Responsibility to compose contract related request which uses
 * data from multiple services
 */
@RestController
public class ContractController {

    private final WebClient.Builder webClientBuilder;

    public ContractController(WebClient.Builder builder) {
        this.webClientBuilder = builder;
    }

    @GetMapping("/contracts")
    public Mono<CustomerContractData> getContracDataForCustomer(@RequestHeader("Authorization") String authHeader,
                                                                @RequestParam("cust_id") Long customerId,
                                                                @RequestParam("car_id") Long carId,
                                                                @RequestParam("interest") BigDecimal interest,
                                                                @RequestParam("mileage") int mileage,
                                                                @RequestParam("duration") int duration) {
        var customerCLient = webClientBuilder.baseUrl("http://localhost:9092").build(); // get url from app properties
        var carCLient = webClientBuilder.baseUrl("http://localhost:9091").build(); // get url from app properties

        var customer = customerCLient.get()
                .uri(uriBuilder -> uriBuilder.path("/customers/{id}").build(customerId))
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToMono(Customer.class);

        var car = carCLient.get()
                .uri(uriBuilder -> uriBuilder.path("/cars/{id}/leaserate")
                        .queryParam("interest", interest)
                        .queryParam("duration", duration)
                        .queryParam("mileage", mileage)
                        .build(carId))
                .header("Authorization", authHeader)
                .retrieve()
                .bodyToMono(LeaseRateResponse.class);

        return Mono.zip(customer, car).map(tuple -> {
            return new CustomerContractData(tuple.getT1(), tuple.getT2());
        });
    }

}
