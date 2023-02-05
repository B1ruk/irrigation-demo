package io.b1ruk.proj.irrigationDemo.irrigationDemo.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity defaultExceptionHandler() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Unable to process request");
    }
}
