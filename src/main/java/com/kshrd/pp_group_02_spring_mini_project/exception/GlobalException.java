package com.kshrd.pp_group_02_spring_mini_project.exception;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.time.Instant;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NoSuchKeyException.class)
    public ProblemDetail handleNoSuchKeyException(){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                "File not found with given fileName");
        detail.setProperty("timestamp", Instant.now());
        return detail;
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ErrorResponse> handleCustomValidation(
            CustomValidationException ex, HttpServletRequest request) {

        ErrorResponse errorBody = ErrorResponse.builder()
                .type("http://localhost:8080/errors/bad-request")
                .instance(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request")
                .timestamp(LocalDateTime.now())
                .errors(ex.getErrors())
                .build();;
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleJsonError(HttpMessageNotReadableException ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(false)
                .message("Invalid input format: Check your Enum values (e.g., status should be COMPLETED)")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
