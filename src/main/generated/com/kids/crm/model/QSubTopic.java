package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSubTopic is a Querydsl query type for SubTopic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubTopic extends EntityPathBase<SubTopic> {

    private static final long serialVersionUID = 1461261784L;

    public static final QSubTopic subTopic = new QSubTopic("subTopic");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSubTopic(String variable) {
        super(SubTopic.class, forVariable(variable));
    }

    public QSubTopic(Path<? extends SubTopic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubTopic(PathMetadata metadata) {
        super(SubTopic.class, metadata);
    }

}

