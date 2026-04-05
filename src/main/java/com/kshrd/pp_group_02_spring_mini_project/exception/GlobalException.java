package com.kshrd.pp_group_02_spring_mini_project.exception;

import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ApiResponse;
import com.kshrd.pp_group_02_spring_mini_project.model.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import software.amazon.awssdk.regions.internal.util.Ec2MetadataConfigProvider;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.net.URI;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NoSuchKeyException.class)
    public ProblemDetail handleNoSuchKeyException(){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                "File not found with given fileName");
        detail.setProperty("timestamp",Instant.now());
        return detail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleJsonError(HttpMessageNotReadableException ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(false)
                .message("Invalid input format: Check your Enum values (e.g., status should be COMPLETED , MISSED , SKIPPED)")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundExceptionHandler.class)
    public ProblemDetail handleRuntimeException(NotFoundExceptionHandler ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        detail.setProperty("timestamp", Instant.now());
        detail.setTitle("Resource not found");
        detail.setProperty("timestamp",Instant.now());
        return detail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setType(URI.create("about:blank"));
        Map<String, Object> errors = new HashMap<>();
        for(FieldError e : ex.getBindingResult().getFieldErrors()) {
            errors.put(e.getField(), e.getDefaultMessage());
        }
        detail.setProperty("errors", errors);
        detail.setProperty("timestamp",Instant.now());
        return detail;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException ex) {
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        Map<String, Object> errors = new HashMap<>();
        for (ParameterValidationResult result : ex.getParameterValidationResults()) {
            String parameter = result.getMethodParameter().getParameterName();
            for (var errorMessage : result.getResolvableErrors()) {
                errors.put(parameter, errorMessage.getDefaultMessage());
            }
        }
        detail.setProperty("errors", errors);
        detail.setProperty("timestamp",Instant.now());
        return detail;
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ProblemDetail alreadyExistsExceptionHandler(AlreadyExistsException ex){
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        detail.setType(URI.create("about:blank"));
        detail.setTitle("Conflict");
        detail.setDetail(ex.getMessage());
        detail.setProperty("timestamp",Instant.now());
        return detail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail badCredentialsExceptionHandler(BadCredentialsException ex){
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setType(URI.create("about:blank"));
        detail.setTitle("Bed Request");
        String errorMessage = "Invalid username, email, or password. Please check your credentials and try again.";
        if(ex.getMessage().equals("NOT_VERIFIED")){
            errorMessage = "Your email address is not yet verified. Please verify your email before logging in.";
        }
        detail.setDetail(errorMessage);
        detail.setProperty("timestamp",Instant.now());
        return detail;
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail badRequestException(BadRequestException exception){
        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setType(URI.create("about:blank"));
        detail.setTitle("Bad Request");
        detail.setDetail(exception.getMessage());
        detail.setProperty("timestamp",Instant.now());
        return detail;
    }
}
