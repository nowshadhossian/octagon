package com.kids.crm.controller.api;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kids.crm.model.User;
import com.kids.crm.service.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class RestApiManager {
    @Autowired
    private JwtToken jwtToken;

    public long getUserId(HttpServletRequest request){
        String token = getJwtTokenFromRequest(request);
        return jwtToken.verifyToken(token)
            .map(decodedJWT -> decodedJWT.getClaim("userId"))
            .map(Claim::asLong)
            .orElse(0l);
    }

    public String getJwtTokenFromRequest(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader.substring(authorizationHeader.indexOf(" ") + 1);
    }
}
