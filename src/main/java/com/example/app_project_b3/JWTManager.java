package com.example.app_project_b3;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTManager {
    private static final String SECRET = "mySecretKey";

    public static String generateToken(String issuer, String subject, long expiresInMs) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiresInMs))
                .sign(algorithm);
    }
    public static boolean verifyToken(String token, String issuer, String subject) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .build()
                    .verify(token);
            return jwt != null;
        } catch (Exception e) {
            return false;
        }
    }
}


