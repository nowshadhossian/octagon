package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentBatchInterest is a Querydsl query type for StudentBatchInterest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudentBatchInterest extends EntityPathBase<StudentBatchInterest> {

    private static final long serialVersionUID = -105957486L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentBatchInterest studentBatchInterest = new QStudentBatchInterest("studentBatchInterest");

    public final QBatch batch;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStudent student;

    public QStudentBatchInterest(String variable) {
        this(StudentBatchInterest.class, forVariable(variable), INITS);
    }

    public QStudentBatchInterest(Path<? extends StudentBatchInterest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentBatchInterest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentBatchInterest(PathMetadata metadata, PathInits inits) {
        this(StudentBatchInterest.class, metadata, inits);
    }

    public QStudentBatchInterest(Class<? extends StudentBatchInterest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.batch = inits.isInitialized("batch") ? new QBatch(forProperty("batch"), inits.get("batch")) : null;
        this.student = inits.isInitialized("student") ? new QStudent(forProperty("student")) : null;
    }

}

