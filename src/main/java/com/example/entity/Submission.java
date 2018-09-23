package com.example.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "submission")
@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id", updatable = false, insertable = false)
    private Long submissionId;

    @Column(name = "student_id")
    private String studentId;

    private int attempt;

    private String status;

    @LastModifiedDate
    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @OneToMany(mappedBy = "submission")
    private List<Evaluation> evaluations;

    public static Submission create(String status, String studentName) {
        Submission subm = new Submission();
        subm.setStudentId(studentName);
        subm.setAttempt(RandomUtils.nextInt(2, 65));
        subm.setStatus(status);
        subm.setDateUpdated(LocalDate.now());
        return subm;
    }
}
