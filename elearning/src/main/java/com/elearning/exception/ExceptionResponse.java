package com.elearning.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {

    private Integer statusCode;
    private String message;
    private Long timeStamp;
}
