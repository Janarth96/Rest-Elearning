package com.elearning.dto;

import java.util.List;

import com.elearning.entity.AssignmentSubmission;
import com.elearning.entity.QuizSubmission;
import com.elearning.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProgressResponseDto {
    
    private Integer studentId;
    private List<AssignmentSubmission> assignmentsMapped;
    private List<QuizSubmission> quizzesMapped; 
}
