package com.kids.crm.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudent is a Querydsl query type for Student
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudent extends EntityPathBase<Student> {

    private static final long serialVersionUID = 1421995538L;

    public static final QStudent student = new QStudent("student");

    public final QUser _super = new QUser(this);

    public final StringPath address = createString("address");

    public final ListPath<Batch, QBatch> batches = this.<Batch, QBatch>createList("batches", Batch.class, QBatch.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> dateOfBirth = createDateTime("dateOfBirth", java.util.Date.class);

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final StringPath firstName = _super.firstName;

    public final StringPath gender = createString("gender");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastName = _super.lastName;

    //inherited
    public final StringPath password = _super.password;

    public final StringPath phone = createString("phone");

    public final ListPath<StudentReferee, QStudentReferee> referees = this.<StudentReferee, QStudentReferee>createList("referees", StudentReferee.class, QStudentReferee.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<Role> role = _super.role;

    public final StringPath school = createString("school");

    public final ListPath<StudentBatchInterest, QStudentBatchInterest> studentBatchInterests = this.<StudentBatchInterest, QStudentBatchInterest>createList("studentBatchInterests", StudentBatchInterest.class, QStudentBatchInterest.class, PathInits.DIRECT2);

    public QStudent(String variable) {
        super(Student.class, forVariable(variable));
    }

    public QStudent(Path<? extends Student> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudent(PathMetadata metadata) {
        super(Student.class, metadata);
    }

}

