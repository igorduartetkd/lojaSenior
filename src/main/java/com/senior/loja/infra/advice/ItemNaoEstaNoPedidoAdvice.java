package com.senior.loja.infra.advice;

import com.senior.loja.business.exception.ItemNaoEstaNoPedidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ItemNaoEstaNoPedidoAdvice {

    @ExceptionHandler(ItemNaoEstaNoPedidoException.class)
    ResponseEntity<?> itemNaoEstaNoPedidoException(ItemNaoEstaNoPedidoException itemNaoEstaNoPedidoException) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.CONFLICT.value());
        error.put("message", itemNaoEstaNoPedidoException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
