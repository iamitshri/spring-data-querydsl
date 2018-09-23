package com.example.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.app.EvaluationStatus;
import com.example.entity.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long>, QuerydslPredicateExecutor<Submission> {

    List<Submission> findByStudentIdOrderByDateUpdatedDesc(String studentId);


    Submission findByStudentIdAndSubmissionId(String studentId, Long subId);

    Submission findByStudentIdIgnoreCase(String studentId);



    List<Submission> findByStatusIn(List<String> status);



    List<Submission> findByEvaluationsEvaluatorUserIdAndEvaluationsStatus(String userId, EvaluationStatus status);

    List<Submission> findByEvaluationsEvaluatorUserId(List<String> userId);

    List<Submission> findByEvaluationsStatus(EvaluationStatus status);

    List<Submission> findByStatusOrderByDateUpdatedDesc(List<String> status);

    @Query("Select eval.submission from Evaluation eval where eval.evaluator.userId in ( :userIdList)")
    List<Submission> getSubmissionsForEvaluators(@Param(value = "userIdList") List<String> userIdList);
}
