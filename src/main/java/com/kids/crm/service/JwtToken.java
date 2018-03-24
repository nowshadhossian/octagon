package com.kids.crm.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtToken {
    private static final String SECRET= "Helo32Fe(&@$sdA2f3$#";

    public String createToken(long userId, String role, String examSettingsDtoEncrypted){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            String token = JWT.create()
                    .withIssuer("octagon")
                    .withClaim("userId", userId)
                    .withClaim("role", role)
                    .withClaim("examSettingsDtoEncrypted", examSettingsDtoEncrypted)
                    .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(120).atZone(ZoneId.systemDefault()).toInstant()))
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return "";
    }

    public Optional<DecodedJWT> verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("octagon")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return Optional.ofNullable(jwt);
        } catch (UnsupportedEncodingException | JWTVerificationException exception){
            //UTF-8 encoding not supported
            return Optional.empty();
        }
    }
}
