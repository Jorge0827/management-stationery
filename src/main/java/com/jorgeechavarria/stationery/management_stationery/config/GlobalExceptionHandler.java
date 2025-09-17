package com.jorgeechavarria.stationery.management_stationery.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.jorgeechavarria.stationery.management_stationery.exceptions.RolNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RolNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleRolNotFoundException(
            RolNotFoundException ex, WebRequest request) {
        
        log.warn("Rol no encontrado: {}", ex.getMessage());
        
        String path = request.getDescription(false).replace("uri=", "");
        ApiErrorResponse errorResponse = new ApiErrorResponse(
            HttpStatus.NOT_FOUND, 
            ex.getMessage(), 
            path
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        
        log.error("Error interno del servidor", ex);
        
        String path = request.getDescription(false).replace("uri=", "");
        ApiErrorResponse errorResponse = new ApiErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR, 
            "Ha ocurrido un error inesperado", 
            path
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
