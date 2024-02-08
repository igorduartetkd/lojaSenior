package com.senior.loja.infra.advice;


import com.senior.loja.business.exception.PedidoFechadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PedidoFechadoAdvice {

    @ExceptionHandler(PedidoFechadoException.class)
    ResponseEntity<?> pedidoFechadoException(PedidoFechadoException pedidoFechadoException) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.put("message", pedidoFechadoException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
}
