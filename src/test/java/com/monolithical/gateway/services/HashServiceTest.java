package com.monolithical.gateway.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashServiceTest {

    @Test
    void hash() {
        // helper to get a hashed pass
        var hashService= new HashService(12);
        System.out.println(hashService.hash("Welkom01"));
    }

    @Test
    void matchHash(){
        var hash = "$2a$12$YR1T3tT6f/5ebewUBZH1A.23C2DkPoWOUees1EF5EXpOBis99L6QS";
        var password = "Welkom01";
        var hashService= new HashService(12);
        assertTrue(hashService.matchHash(password, hash));
    }
}
