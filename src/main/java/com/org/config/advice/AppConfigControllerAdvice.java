package com.org.config.advice;

import com.org.config.exception.AppConfigNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@ControllerAdvice(annotations = RestController.class)
@ResponseBody
class AppConfigControllerAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(AppConfigNotFoundException.class)
    public ResponseEntity<VndErrors> configNotFoundException(AppConfigNotFoundException ex) {
        return this.error(ex, HttpStatus.NOT_FOUND, ex.getAppcode());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<VndErrors> appConfigException(RuntimeException ex) {
        return this.error(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.toString());
    }

    private <E extends Exception> ResponseEntity<VndErrors> error(E e, HttpStatus httpStatus, String logref) {
        String message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("application/json"));
        return new ResponseEntity<VndErrors>(new VndErrors(logref, message), httpHeaders, httpStatus);
    }
}
