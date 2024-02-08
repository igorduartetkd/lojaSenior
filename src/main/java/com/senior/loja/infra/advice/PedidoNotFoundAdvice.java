package com.senior.loja.infra.advice;


import com.senior.loja.business.exception.PedidoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PedidoNotFoundAdvice {

    @ExceptionHandler(PedidoNotFoundException.class)
    ResponseEntity<?> pedidoNotfoundException(PedidoNotFoundException pedidoNotfoundException) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("message", pedidoNotfoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
