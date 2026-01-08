package com.ra.advice;

import com.ra.exception.CustomException;
import com.ra.model.dto.response.DataResponse;
import org.apache.http.MethodNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<DataResponse<?>> handleCustomException(CustomException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DataResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<DataResponse<?>> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(DataResponse.builder()
                .message("Method Not Allowed Exception")
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .build());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DataResponse.builder()
                        .data(errors)
                        .message("Bad Request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build());

    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DataResponse<?>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DataResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                .message("Bad Request")
                .build());
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<DataResponse<?>> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataResponse.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Not Found")
                .build());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResponse<?>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DataResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build());
    }
}
