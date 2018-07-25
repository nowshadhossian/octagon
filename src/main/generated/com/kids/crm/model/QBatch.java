package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBatch is a Querydsl query type for Batch
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBatch extends EntityPathBase<Batch> {

    private static final long serialVersionUID = 52251857L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBatch batch = new QBatch("batch");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSession session;

    public final ListPath<Student, QStudent> students = this.<Student, QStudent>createList("students", Student.class, QStudent.class, PathInits.DIRECT2);

    public final QSubject subject;

    public final QTeacher teacher;

    public QBatch(String variable) {
        this(Batch.class, forVariable(variable), INITS);
    }

    public QBatch(Path<? extends Batch> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBatch(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBatch(PathMetadata metadata, PathInits inits) {
        this(Batch.class, metadata, inits);
    }

    public QBatch(Class<? extends Batch> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.session = inits.isInitialized("session") ? new QSession(forProperty("session")) : null;
        this.subject = inits.isInitialized("subject") ? new QSubject(forProperty("subject")) : null;
        this.teacher = inits.isInitialized("teacher") ? new QTeacher(forProperty("teacher")) : null;
    }

}

