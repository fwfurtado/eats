package me.fwfurtado.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class LocationExceptionHandler {

    Class clazz;
    private static final Logger LOG =  LoggerFactory.getLogger(LocationExceptionHandler.class);

    @ExceptionHandler({ZipCodeNotFoundException.class,LatLonNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void handle(Exception e) {
        LOG.error("Cannot find some information", e);
    }
}
