package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSession is a Querydsl query type for Session
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSession extends EntityPathBase<Session> {

    private static final long serialVersionUID = 991161965L;

    public static final QSession session = new QSession("session");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QSession(String variable) {
        super(Session.class, forVariable(variable));
    }

    public QSession(Path<? extends Session> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSession(PathMetadata metadata) {
        super(Session.class, metadata);
    }

}

