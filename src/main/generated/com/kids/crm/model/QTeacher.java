package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeacher is a Querydsl query type for Teacher
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeacher extends EntityPathBase<Teacher> {

    private static final long serialVersionUID = 1861564345L;

    public static final QTeacher teacher = new QTeacher("teacher");

    public final QUser _super = new QUser(this);

    public final StringPath address = createString("address");

    public final SetPath<Batch, QBatch> batches = this.<Batch, QBatch>createSet("batches", Batch.class, QBatch.class, PathInits.DIRECT2);

    public final StringPath degree = createString("degree");

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final StringPath firstName = _super.firstName;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastName = _super.lastName;

    //inherited
    public final StringPath password = _super.password;

    public final StringPath phone = createString("phone");

    //inherited
    public final EnumPath<Role> role = _super.role;

    public QTeacher(String variable) {
        super(Teacher.class, forVariable(variable));
    }

    public QTeacher(Path<? extends Teacher> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeacher(PathMetadata metadata) {
        super(Teacher.class, metadata);
    }

}

