package com.example.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import com.example.entity.Evaluation;
import com.example.entity.QSubmission;
import com.example.entity.QUser;
import com.example.entity.Submission;
import com.example.entity.User;
import com.example.repo.EvaluationRepository;
import com.example.repo.SubmissionRepository;
import com.example.repo.UserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class SubmissionSearch implements ApplicationRunner {

    private final UserRepository userRepo;

    private final SubmissionRepository submissionRepo;

    private final EvaluationRepository evaluationRepo;

    private final EntityManager em;

    List<Pair<String, String>> evaluatorFirstLastNames = new ArrayList<>();

    {
        evaluatorFirstLastNames.add(Pair.of("Diana", "Admin"));
        evaluatorFirstLastNames.add(Pair.of("Amit", "Shri"));
        evaluatorFirstLastNames.add(Pair.of("Mike", "Sales"));
        evaluatorFirstLastNames.add(Pair.of("Sara", "Admin"));
        evaluatorFirstLastNames.add(Pair.of("Randy", "Sales"));
        evaluatorFirstLastNames.add(Pair.of("Charlie", "IT"));
        evaluatorFirstLastNames.add(Pair.of("Rose", "IT"));
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {

      


        QSubmission submission = QSubmission.submission;
        BooleanExpression submissionById = submission.submissionId.eq(1L);
        BooleanExpression submissionByStatus = submission.status.equalsIgnoreCase(SubmissionStatus.FAILED.toString());
        BooleanExpression submissionByStudentId = submission.studentId.equalsIgnoreCase("ramesh");

        Iterable<Submission> result = submissionRepo.findAll(submissionById);
        result.forEach(s -> log.debug("Search by Id: id:{},  status:{}  ", s.getStudentId(), s.getStatus()));

        result = submissionRepo.findAll(submissionByStatus);
        result.forEach(s -> log.debug("Search by Status: id:{},  status:{}  ", s.getStudentId(), s.getStatus()));

        result = submissionRepo.findAll(submissionByStudentId);
        result.forEach(s -> log.debug("Search by Student Id: id:{},  status:{}  ", s.getStudentId(), s.getStatus()));

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QUser x = submission.evaluations.any().evaluator;

        BooleanExpression evaluatorFirstName = x.firstName.equalsIgnoreCase("Charlie");
        BooleanExpression evaluatorLastName = x.lastName.equalsIgnoreCase("IT");
        result = submissionRepo.findAll(evaluatorFirstName.and(evaluatorLastName));
        result.forEach(s -> log.debug("Search by Evaluator: submission id:{},  student id:{},  status:{}",
                s.getSubmissionId(), s.getStudentId(), s.getStatus()));
        
        submissionRepo.findAll(evaluatorLastName).forEach(s -> log.debug("Search by last name of Evaluator: submission id:{},  student id:{},  status:{}",
                s.getSubmissionId(), s.getStudentId(), s.getStatus()));;

    }


    /**
     * Create Data so we can search on submission using {@code SearchCriteria}
     */
    private void createData() {
        createUsers();

        submissionRepo.saveAll(createSubmission());

        evaluationRepo.save(createEvaluation(Pair.of("Diana", "Admin"), EvaluationStatus.WORKING, "ramesh"));
        evaluationRepo.save(createEvaluation(Pair.of("Mike", "Sales"), EvaluationStatus.WORKING, "ramesh"));
        evaluationRepo.save(createEvaluation(Pair.of("Rose", "IT"), EvaluationStatus.WORKING, "dinesh"));
        evaluationRepo.save(createEvaluation(Pair.of("Sara", "Admin"), EvaluationStatus.WORKING, "vijay"));
        evaluationRepo.save(createEvaluation(Pair.of("Charlie", "IT"), EvaluationStatus.WORKING, "sanjay"));
        evaluationRepo.save(createEvaluation(Pair.of("Randy", "Sales"), EvaluationStatus.WORKING, "dinesh"));
    }



    List<User> createUsers() {

        List<User> userList = new ArrayList<>();
        for (Pair<String, String> x : evaluatorFirstLastNames) {
            userList.add(User.create(x.getFirst(), x.getSecond()));
        }

        return addUser(userList);
    }

    User addUser(String firstName, String lastName) {
        return userRepo.save(User.create(firstName, lastName));
    }

    List<User> addUser(List<User> users) {
        return userRepo.saveAll(users);
    }

    List<Submission> createSubmission() {
        return Arrays.asList(Submission.create(SubmissionStatus.EVALUATED.toString(), "ramesh"),
                Submission.create(SubmissionStatus.SUBMITTED.toString(), "dinesh"),
                Submission.create(SubmissionStatus.PASSED.toString(), "vijay"),
                Submission.create(SubmissionStatus.REVISION_NEEDED.toString(), "anand"),
                Submission.create(SubmissionStatus.FAILED.toString(), "farukh"),
                Submission.create(SubmissionStatus.EVALUATION_AUDIT.toString(), "sanjay"));
    }

    Evaluation createEvaluation(Pair<String, String> evalfnln, EvaluationStatus status, String studentFirstName) {
        List<User> evalEntity = userRepo.findByFirstNameAndLastNameAllIgnoreCase(evalfnln.getFirst(), evalfnln.getSecond());
        if (CollectionUtils.isEmpty(evalEntity)) {
            throw new IllegalArgumentException("evaluator not found");
        }
        return Evaluation.create(evalEntity.get(0), status, submissionRepo.findByStudentIdIgnoreCase(studentFirstName));
    }
}
