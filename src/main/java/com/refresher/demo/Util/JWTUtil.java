package com.refresher.demo.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private String secret;
    private Algorithm algo = Algorithm.HMAC256("Blablabla");

    public String generateToken(String username) throws IllegalArgumentException, JWTCreationException {
        return JWT.create().withSubject("User Details").withClaim("username", username).withIssuedAt(new Date()).withIssuer("com.refresher.demo").sign(algo);
    }

    public String verifyToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(algo).withSubject("User Details").withIssuer("com.refresher.demo").build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}
