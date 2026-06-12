package com.employeemanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.error("ResourceNotFoundException: {}", exception.getMessage());
        
       ErrorResponse errorResponse = ErrorResponse.builder()
               .status(404)
               .message(exception.getMessage())
               .timestamp(LocalDateTime.now())
               .path(getCurrentPath())
               .build();
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
//        log.error("IllegalArgumentException: {}", exception.getMessage());
//
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .status(HttpStatus.BAD_REQUEST.value())
//                .message(exception.getMessage())
//                .timestamp(LocalDateTime.now())
//                .path(getCurrentPath())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//    }
//
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
        log.error("Unexpected exception: ", exception);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred: " + exception.getMessage())
                .timestamp(LocalDateTime.now())
                .path(getCurrentPath())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    private String getCurrentPath() {
        try {
            return ServletUriComponentsBuilder.fromCurrentRequest()
                    .toUriString();
        } catch (Exception e) {
            return "unknown";
        }
    }

}


