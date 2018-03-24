package com.kids.crm.controller.api;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kids.crm.model.Batch;
import com.kids.crm.model.ExamType;
import com.kids.crm.model.User;
import com.kids.crm.pojo.ExamSettingsDTO;
import com.kids.crm.repository.BatchRepository;
import com.kids.crm.repository.UserRepository;
import com.kids.crm.service.Encryption;
import com.kids.crm.service.JwtToken;
import com.kids.crm.service.exception.BatchNotFoundException;
import com.kids.crm.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RestApiManager {
    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    BatchRepository batchRepository;

    private ExamSettingsDTO examSettingsDTO;
    private long userId = -1;

    public long getUserId() {
        String token = getJwtTokenFromRequest();
        return getUserId(token);
    }

    /**
     * Don't use it. use {RestApiManager::getUserId()}
     * @Deprecate not really deprecate. A marker not to use
     * @param jwtTokenn
     * @return
     */
    @Deprecated
    public long getUserId(String jwtTokenn) {
        if(userId == -1){
            fetchValuesFromJwtToken(jwtTokenn);
        }
        return userId;
    }

    private void fetchValuesFromJwtToken(){
        fetchValuesFromJwtToken(getJwtTokenFromRequest());
    }

    private void fetchValuesFromJwtToken(String jwtTokenn){
        jwtToken.verifyToken(jwtTokenn)
                .ifPresent(decodedJWT -> {
                    userId = decodedJWT.getClaim("userId").asLong();
                    try {
                        examSettingsDTO = objectMapper.readValue(Encryption.decrypt(decodedJWT.getClaim("examSettingsDtoEncrypted").asString()), ExamSettingsDTO.class);
                    } catch (IOException e) {
                        //e.printStackTrace(); need logger
                    }
                });
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

    public Batch getCurrentBatch(){ //TODO BUG
        if(examSettingsDTO == null){
            fetchValuesFromJwtToken();
        }
        return batchRepository.findById(examSettingsDTO.getBatchId()).orElseThrow(BatchNotFoundException::new);
    }

    public ExamSettingsDTO getExamSettingsDTO(){
        if(examSettingsDTO == null){
            fetchValuesFromJwtToken();
        }
        return examSettingsDTO;
    }
}
