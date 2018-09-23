package com.example.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubmission is a Querydsl query type for Submission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubmission extends EntityPathBase<Submission> {

    private static final long serialVersionUID = 197829798L;

    public static final QSubmission submission = new QSubmission("submission");

    public final NumberPath<Integer> attempt = createNumber("attempt", Integer.class);

    public final DatePath<java.time.LocalDate> dateUpdated = createDate("dateUpdated", java.time.LocalDate.class);

    public final ListPath<Evaluation, QEvaluation> evaluations = this.<Evaluation, QEvaluation>createList("evaluations", Evaluation.class, QEvaluation.class, PathInits.DIRECT2);

    public final StringPath status = createString("status");

    public final StringPath studentId = createString("studentId");

    public final NumberPath<Long> submissionId = createNumber("submissionId", Long.class);

    public QSubmission(String variable) {
        super(Submission.class, forVariable(variable));
    }

    public QSubmission(Path<? extends Submission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubmission(PathMetadata metadata) {
        super(Submission.class, metadata);
    }

}

