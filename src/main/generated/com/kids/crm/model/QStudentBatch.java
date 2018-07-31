package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentBatch is a Querydsl query type for StudentBatch
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudentBatch extends EntityPathBase<StudentBatch> {

    private static final long serialVersionUID = -1077283768L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentBatch studentBatch = new QStudentBatch("studentBatch");

    public final QBatch batch;

    public final EnumPath<BatchStatusType> batchStatusType = createEnum("batchStatusType", BatchStatusType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStudent student;

    public QStudentBatch(String variable) {
        this(StudentBatch.class, forVariable(variable), INITS);
    }

    public QStudentBatch(Path<? extends StudentBatch> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentBatch(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentBatch(PathMetadata metadata, PathInits inits) {
        this(StudentBatch.class, metadata, inits);
    }

    public QStudentBatch(Class<? extends StudentBatch> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.batch = inits.isInitialized("batch") ? new QBatch(forProperty("batch"), inits.get("batch")) : null;
        this.student = inits.isInitialized("student") ? new QStudent(forProperty("student")) : null;
    }

}

