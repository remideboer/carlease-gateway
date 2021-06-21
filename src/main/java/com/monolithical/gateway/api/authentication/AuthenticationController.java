package com.monolithical.gateway.api.authentication;

import com.monolithical.gateway.repositories.UserRepository;
import com.monolithical.gateway.services.HashService;
import com.monolithical.gateway.services.jwt.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationController {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final HashService hashService;
    private TokenGenerator tokenGenerator;
    private UserRepository userRepository;

    public AuthenticationController(TokenGenerator tokenGenerator, UserRepository userRepository, HashService hashService) {
        this.tokenGenerator = tokenGenerator;
        this.userRepository = userRepository;
        this.hashService = hashService;
    }

    @PostMapping("/authenticate")
    Mono<ResponseEntity<?>> authenticate(@RequestBody AuthenticationRequestBody authentication) {
        var user = userRepository.findByUsername(authentication.getUsername());
        logger.debug("user is: " + user);
        logger.debug("users:" + userRepository.findAll());
        if (user != null && hashService.matchHash(authentication.getPassword(), user.getHash())) {
            return Mono.just(ResponseEntity.ok((tokenGenerator.generateToken(user))));
        } else {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
    }

}
