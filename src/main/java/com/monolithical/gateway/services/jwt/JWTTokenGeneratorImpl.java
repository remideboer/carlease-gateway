package com.monolithical.gateway.services.jwt;

import com.monolithical.gateway.domain.Token;
import com.monolithical.gateway.domain.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenGeneratorImpl implements TokenGenerator {
    private static Logger logger = LoggerFactory.getLogger(JWTTokenGeneratorImpl.class);
    private final long expiration;
    private byte[] secret;

    public JWTTokenGeneratorImpl(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
        this.secret = secret.getBytes();
        this.expiration = expiration;
        logger.debug("USING SECRET: " + secret);
    }

    @Override
    public Token generateToken(User user) {
        try {
            JWSSigner signer = new MACSigner(secret);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issuer("https://carlease.com")
                    .expirationTime(new Date(new Date().getTime() + expiration * 1000)) // expiration in seconds to millis
                    .build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            String jwt = signedJWT.serialize(); // does not base64 encoded signature
            return new Token(jwt);
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Token creation failed"); // should never get here
    }
}
