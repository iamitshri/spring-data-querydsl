package com.example.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.example.app.EvaluationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "evaluation")
@Entity
public class Evaluation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id", updatable = false, insertable = false)
    private Long evaluationId;

    @OneToOne(optional = false)
    @JoinColumn(name = "evaluator_id", referencedColumnName = "user_id")
    private User evaluator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "submission_id", referencedColumnName = "submission_id")
    private Submission submission;

    @Column(name = "status", columnDefinition = "enum('WORKING','CANCELLED','COMPLETED', 'REVIEWED')")
    @Enumerated(EnumType.STRING)
    private EvaluationStatus status;


    public static Evaluation create(User evaluatorPerson, EvaluationStatus statusOfEvaluation,
            Submission submissionEvaluated) {
        Evaluation eval = new Evaluation();
        eval.setEvaluator(evaluatorPerson);
        eval.setStatus(statusOfEvaluation);
        eval.setSubmission(submissionEvaluated);
        return eval;
    }



}
