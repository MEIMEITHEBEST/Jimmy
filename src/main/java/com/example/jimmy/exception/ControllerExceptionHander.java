package com.example.jimmy.exception;

import com.example.jimmy.response.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHander {
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e){
        return R.fail(e.getMessage());
    }
}
