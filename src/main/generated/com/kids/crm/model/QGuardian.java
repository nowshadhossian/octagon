package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGuardian is a Querydsl query type for Guardian
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGuardian extends EntityPathBase<Guardian> {

    private static final long serialVersionUID = 2021116058L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGuardian guardian = new QGuardian("guardian");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath relation = createString("relation");

    public final QStudent student;

    public QGuardian(String variable) {
        this(Guardian.class, forVariable(variable), INITS);
    }

    public QGuardian(Path<? extends Guardian> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGuardian(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGuardian(PathMetadata metadata, PathInits inits) {
        this(Guardian.class, metadata, inits);
    }

    public QGuardian(Class<? extends Guardian> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.student = inits.isInitialized("student") ? new QStudent(forProperty("student")) : null;
    }

}

