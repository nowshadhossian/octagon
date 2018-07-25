package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentAnswer is a Querydsl query type for StudentAnswer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudentAnswer extends EntityPathBase<StudentAnswer> {

    private static final long serialVersionUID = 947307632L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentAnswer studentAnswer = new QStudentAnswer("studentAnswer");

    public final StringPath answer = createString("answer");

    public final DateTimePath<java.util.Date> attendedOn = createDateTime("attendedOn", java.util.Date.class);

    public final QBatch batch;

    public final EnumPath<ExamType> examType = createEnum("examType", ExamType.class);

    public final BooleanPath gotCorrect = createBoolean("gotCorrect");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QQuestion question;

    public final QSubject subject;

    public final QUser user;

    public QStudentAnswer(String variable) {
        this(StudentAnswer.class, forVariable(variable), INITS);
    }

    public QStudentAnswer(Path<? extends StudentAnswer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentAnswer(PathMetadata metadata, PathInits inits) {
        this(StudentAnswer.class, metadata, inits);
    }

    public QStudentAnswer(Class<? extends StudentAnswer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.batch = inits.isInitialized("batch") ? new QBatch(forProperty("batch"), inits.get("batch")) : null;
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question"), inits.get("question")) : null;
        this.subject = inits.isInitialized("subject") ? new QSubject(forProperty("subject")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

