package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -1909699857L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final BooleanPath active = createBoolean("active");

    public final StringPath answer = createString("answer");

    public final StringPath answerExplanation = createString("answerExplanation");

    public final NumberPath<Integer> curriculum = createNumber("curriculum", Integer.class);

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> paper = createNumber("paper", Integer.class);

    public final NumberPath<Integer> questionNo = createNumber("questionNo", Integer.class);

    public final QSession session;

    public final QSubject subject;

    public final SetPath<SubTopic, QSubTopic> subTopics = this.<SubTopic, QSubTopic>createSet("subTopics", SubTopic.class, QSubTopic.class, PathInits.DIRECT2);

    public final QTopic topic;

    public final DateTimePath<java.util.Date> uploadDate = createDateTime("uploadDate", java.util.Date.class);

    public final NumberPath<Integer> variant = createNumber("variant", Integer.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.session = inits.isInitialized("session") ? new QSession(forProperty("session")) : null;
        this.subject = inits.isInitialized("subject") ? new QSubject(forProperty("subject")) : null;
        this.topic = inits.isInitialized("topic") ? new QTopic(forProperty("topic"), inits.get("topic")) : null;
    }

}

