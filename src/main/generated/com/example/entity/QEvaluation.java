package com.example.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvaluation is a Querydsl query type for Evaluation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEvaluation extends EntityPathBase<Evaluation> {

    private static final long serialVersionUID = -1161793386L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEvaluation evaluation = new QEvaluation("evaluation");

    public final NumberPath<Long> evaluationId = createNumber("evaluationId", Long.class);

    public final QUser evaluator;

    public final EnumPath<com.example.app.EvaluationStatus> status = createEnum("status", com.example.app.EvaluationStatus.class);

    public final QSubmission submission;

    public QEvaluation(String variable) {
        this(Evaluation.class, forVariable(variable), INITS);
    }

    public QEvaluation(Path<? extends Evaluation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEvaluation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEvaluation(PathMetadata metadata, PathInits inits) {
        this(Evaluation.class, metadata, inits);
    }

    public QEvaluation(Class<? extends Evaluation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.evaluator = inits.isInitialized("evaluator") ? new QUser(forProperty("evaluator")) : null;
        this.submission = inits.isInitialized("submission") ? new QSubmission(forProperty("submission")) : null;
    }

}

