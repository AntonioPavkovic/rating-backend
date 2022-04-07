package com.internship.ratingbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.http.HttpStatus.*;

/**
 * EntityExceptionHandler - handles exceptions such as ArgumentNotValid, IllegalArgumentException
 * and EntityNotFoundException
 */

@ControllerAdvice
public class EntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Method that handles non-valid arguments
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity with an error message and BAD_REQUEST status code
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String filedName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(filedName, message);
        });
        return new ResponseEntity<>(errors, BAD_REQUEST);
    }

    /**
     * Method that handles illegal argument exceptions
     *
     * @param ex
     * @return ResponseEntity with an apiError and BAD_REQUEST status code
     */

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiError apiError=new ApiError(BAD_REQUEST,ex);
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }


    /**
     * Method that handles Not Found Exceptions
     *
     * @param ex
     * @return ResponseEntity with an apiError and NOT_FOUND status code
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiError apiError=new ApiError(NOT_FOUND,ex);
        return new ResponseEntity<>(apiError, NOT_FOUND);
    }

}
