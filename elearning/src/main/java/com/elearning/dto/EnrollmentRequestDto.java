package com.elearning.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnrollmentRequestDto {
    @NotNull
    private Integer studentId;
    @NotNull
    private Integer courseID;
}
