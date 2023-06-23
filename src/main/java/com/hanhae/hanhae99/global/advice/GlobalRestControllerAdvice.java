package com.hanhae.hanhae99.global.advice;

import com.hanhae.hanhae99.global.exception.CustomException;
import com.hanhae.hanhae99.global.response.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class GlobalRestControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> applicationHandler(CustomException e){
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(JsonResponse.error(e.getErrorCode().name(),e.getErrorCode().getMessage()));
    }



}
