package com.kshrd.pp_group_02_spring_mini_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NoSuchKeyException.class)
    public ProblemDetail handleNoSuchKeyException(){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                "File not found with given fileName");
        detail.setProperty("timestamp", Instant.now());
        return detail;
    }
}
