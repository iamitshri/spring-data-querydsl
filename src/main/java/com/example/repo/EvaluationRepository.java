package com.example.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.example.app.EvaluationStatus;
import com.example.entity.Evaluation;
import com.example.entity.Submission;


@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long>,
QuerydslPredicateExecutor<Evaluation>
{
  List<Evaluation> findBySubmissionSubmissionId(Long submissionId);

  List<Evaluation> findByEvaluatorUserIdAndStatus(String userId, EvaluationStatus status);

  List<Evaluation> findBySubmissionSubmissionId(List<Submission> submission);


  Evaluation findByEvaluationId(Long evalId);

 
  /**
   * Find a List of Evaluation records by the evaluatorId.
   * 
   * @param evaluatorId
   * @return List
   */
  List<Evaluation> findByEvaluatorUserId(final long evaluatorId);

  /**
   * Find a List of Evaluation records by the submissionId.
   * 
   * @param submissionId
   * @return List
   */
  List<Evaluation> findBySubmissionSubmissionId(final long submissionId);

  /**
   * Find the evaluation entity only if it's id and evaluator equal those in the arguments
   * 
   * @param evaluationId
   * @param evaluatorId
   * @return
   */
  Optional<Evaluation> findByEvaluationIdAndEvaluatorUserId(Long evaluationId,
      String evaluatorId);
}
