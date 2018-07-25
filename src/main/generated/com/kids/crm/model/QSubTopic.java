package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubTopic is a Querydsl query type for SubTopic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubTopic extends EntityPathBase<SubTopic> {

    private static final long serialVersionUID = 1461261784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubTopic subTopic = new QSubTopic("subTopic");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QTopic topic;

    public QSubTopic(String variable) {
        this(SubTopic.class, forVariable(variable), INITS);
    }

    public QSubTopic(Path<? extends SubTopic> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubTopic(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubTopic(PathMetadata metadata, PathInits inits) {
        this(SubTopic.class, metadata, inits);
    }

    public QSubTopic(Class<? extends SubTopic> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.topic = inits.isInitialized("topic") ? new QTopic(forProperty("topic"), inits.get("topic")) : null;
    }

}

