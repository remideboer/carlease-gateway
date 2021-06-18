package com.monolithical.gateway.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashService {

    private final PasswordEncoder encoder;

    public HashService(@Value("${hash.strength}") int strength) {
        this.encoder = new BCryptPasswordEncoder(strength);
    }

    public String hash(String password) {
        return encoder.encode(password);
    }

    public boolean matchHash(String password, String hash) {
        return encoder.matches(password, hash);
    }
}
