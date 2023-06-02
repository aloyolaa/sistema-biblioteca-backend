package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    private static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail entityNotFoundExceptionImpl(EntityNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Datos no encontrados");
        problemDetail.setType(URI.create("http://localhost:8080/api/errors/not-found"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Campos no cumplen requerimientos");
        problemDetail.setTitle("Campos inválidos");
        problemDetail.setType(URI.create("http://localhost:8080/api/errors/invalid-fields"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    @ExceptionHandler(DataAccessExceptionImpl.class)
    public ProblemDetail dataAccessExceptionImpl(DataAccessExceptionImpl e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMostSpecificCause().getMessage());
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("http://localhost:8080/api/errors/data-access"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail dataIntegrityViolationException(DataIntegrityViolationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMostSpecificCause().getMessage());
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("http://localhost:8080/api/errors/data-integrity"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail illegalArgumentException(IllegalArgumentException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.PARTIAL_CONTENT, e.getMessage());
        problemDetail.setTitle("Identificador necesario");
        problemDetail.setType(URI.create("http://localhost:8080/api/errors/ilegal-argument"));
        problemDetail.setProperty(TIMESTAMP, LocalDateTime.now());
        return problemDetail;
    }
}
