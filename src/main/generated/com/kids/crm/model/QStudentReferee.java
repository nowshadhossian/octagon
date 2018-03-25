package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentReferee is a Querydsl query type for StudentReferee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudentReferee extends EntityPathBase<StudentReferee> {

    private static final long serialVersionUID = 1234234030L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentReferee studentReferee = new QStudentReferee("studentReferee");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath refereeName = createString("refereeName");

    public final QStudent student;

    public final QSubject subject;

    public QStudentReferee(String variable) {
        this(StudentReferee.class, forVariable(variable), INITS);
    }

    public QStudentReferee(Path<? extends StudentReferee> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentReferee(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentReferee(PathMetadata metadata, PathInits inits) {
        this(StudentReferee.class, metadata, inits);
    }

    public QStudentReferee(Class<? extends StudentReferee> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.student = inits.isInitialized("student") ? new QStudent(forProperty("student")) : null;
        this.subject = inits.isInitialized("subject") ? new QSubject(forProperty("subject"), inits.get("subject")) : null;
    }

}

