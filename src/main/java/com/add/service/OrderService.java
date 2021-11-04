package com.add.service;


import com.add.error.BusinessException;
import com.add.service.model.OrderModel;

public interface OrderService {

    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;


}
