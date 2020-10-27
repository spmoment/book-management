package com.bookmanagement.controllers;

import com.bookmanagement.dto.CreateOrderRequest;
import com.bookmanagement.exception.ValidationException;
import com.bookmanagement.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@CrossOrigin
public class OrderController {

    private static final Logger log = Logger.getLogger("OrderController.class");

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<Integer> saveOrder(@RequestBody CreateOrderRequest request) throws ValidationException {

        log.info("Handling save order request: " + request);

        return ResponseEntity.ok(orderService.saveOrder(request));
    }
}
