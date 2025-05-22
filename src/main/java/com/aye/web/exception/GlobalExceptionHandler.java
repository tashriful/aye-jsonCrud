package com.aye.web.exception;

import com.aye.web.dto.ApiResponseDto;
import com.aye.web.utill.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResourceNotFoundException
    @ExceptionHandler(CustomExceptions.ResourceNotFoundException.class)
    public ResponseEntity <ApiResponseDto<Object>> handleResourceNotFoundException(CustomExceptions.ResourceNotFoundException ex) {
        return new ResponseEntity<>(ResponseUtils.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    // Handle BadRequestException
    @ExceptionHandler(CustomExceptions.BadRequestException.class)
    public ResponseEntity <ApiResponseDto<Object>> handleBadRequestException(CustomExceptions.BadRequestException ex) {

        ApiResponseDto<Object> errorMessage = ResponseUtils.error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    // Handle all other exceptions (Generic fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity <ApiResponseDto<Object>> handleGenericException(Exception ex) {
        ApiResponseDto<Object> errorMessage = ResponseUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity <ApiResponseDto<Object>> handleIOException(IOException ex) {
        ApiResponseDto<Object> errorMessage = ResponseUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomExceptions.UnauthorizedAccessException.class)
    public ResponseEntity <ApiResponseDto<Object>> handleUnauthorizedException(CustomExceptions.UnauthorizedAccessException ex) {
        ApiResponseDto<Object> errorMessage = ResponseUtils.error(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CustomExceptions.InternalServerErrorException.class)
    public ResponseEntity <ApiResponseDto<Object>> handleIOException(CustomExceptions.InternalServerErrorException ex) {
        ApiResponseDto<Object> errorMessage = ResponseUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
