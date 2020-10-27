package com.bookmanagement.service;

import com.bookmanagement.dto.CreateOrderRequest;
import com.bookmanagement.exception.ValidationException;

import java.util.List;

public interface OrderService {

    Integer saveOrder(CreateOrderRequest request) throws ValidationException;
}
