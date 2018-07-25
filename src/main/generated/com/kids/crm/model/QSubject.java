package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSubject is a Querydsl query type for Subject
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubject extends EntityPathBase<Subject> {

    private static final long serialVersionUID = 1433256195L;

    public static final QSubject subject = new QSubject("subject");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSubject(String variable) {
        super(Subject.class, forVariable(variable));
    }

    public QSubject(Path<? extends Subject> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubject(PathMetadata metadata) {
        super(Subject.class, metadata);
    }

}

