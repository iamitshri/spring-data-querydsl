package com.example.app;

import java.util.List;
import lombok.Data;

@Data
public class SearchCriteria {
    private String studentId;
    private String evaluatorFirstName;
    private String evaluatorLastName;
    private String status;
    private Long submissionId;
    private String dateRange;
}
