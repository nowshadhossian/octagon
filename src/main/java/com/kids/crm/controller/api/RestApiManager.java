package com.kids.crm.controller.api;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kids.crm.model.Batch;
import com.kids.crm.model.User;
import com.kids.crm.repository.BatchRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.service.JwtToken;
import com.kids.crm.service.exception.BatchNotFoundException;
import com.kids.crm.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RestApiManager {
    @Autowired
    private JwtToken jwtToken;

    private @Autowired
    HttpServletRequest request;
    private @Autowired
    UserRepository userRepository;

    @Autowired
    BatchRepository batchRepository;

    public long getUserId() {
        String token = getJwtTokenFromRequest();
        return getUserId(token);
    }

    public long getUserId(String jwtTokenn) {
        return jwtToken.verifyToken(jwtTokenn)
                .map(decodedJWT -> decodedJWT.getClaim("userId"))
                .map(Claim::asLong)
                .orElse(0l);
    }

    public User getUser() {
        long userId = getUserId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public String getJwtTokenFromRequest() {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader.substring(authorizationHeader.indexOf(" ") + 1);
    }

    public Batch getCurrentBatch(){
        return batchRepository.findById(1l).orElseThrow(BatchNotFoundException::new);
    }
}
