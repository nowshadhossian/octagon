package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAssistant is a Querydsl query type for Assistant
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAssistant extends EntityPathBase<Assistant> {

    private static final long serialVersionUID = -154057483L;

    public static final QAssistant assistant = new QAssistant("assistant");

    public final QUser _super = new QUser(this);

    public final StringPath address = createString("address");

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final StringPath firstName = _super.firstName;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastName = _super.lastName;

    //inherited
    public final StringPath password = _super.password;

    public final StringPath phone = createString("phone");

    //inherited
    public final EnumPath<Role> role = _super.role;

    public QAssistant(String variable) {
        super(Assistant.class, forVariable(variable));
    }

    public QAssistant(Path<? extends Assistant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAssistant(PathMetadata metadata) {
        super(Assistant.class, metadata);
    }

}

