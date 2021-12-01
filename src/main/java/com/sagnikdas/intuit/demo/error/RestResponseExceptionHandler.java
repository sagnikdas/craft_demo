package com.sagnikdas.intuit.demo.error;

import com.sagnikdas.intuit.demo.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(VinNotFoundException.class)
    public ResponseEntity<ErrorMessage> vinNotFoundException(VinNotFoundException vinNotFoundException, WebRequest webRequest) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, vinNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler({InvalidSearchTypeException.class, EmptyVinIdsException.class})
    public ResponseEntity<ErrorMessage> invalidSearchTypeException(Exception exception,
                                                                   WebRequest webRequest) {

        ErrorMessage message = new ErrorMessage();
        if(exception instanceof InvalidSearchTypeException){
            message = new ErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        }

        if(exception instanceof EmptyVinIdsException){
            message = new ErrorMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

}
