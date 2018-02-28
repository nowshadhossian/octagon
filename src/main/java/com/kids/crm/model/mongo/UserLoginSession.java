package com.kids.crm.model.mongo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Builder
@Document
public class UserLoginSession {
    @Id
    private String id;
    private String email;
    private String name;
    private long refUserId;
    private Date login;
    private String ip;
    private String role;

}
