package com.monolithical.gateway.services.jwt;

import com.monolithical.gateway.domain.Token;
import com.monolithical.gateway.domain.User;

public interface TokenGenerator {
    Token generateToken(User user);
}
