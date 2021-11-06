package com.add.service;


import com.add.error.BusinessException;
import com.add.service.model.OrderModel;

public interface OrderService {


    //1.通过前端url上传秒杀活动id，下单接口内校验对应id属于对应商品且活动已经开始
    //2.直接在下单接口内判断对应商品是否存在秒杀活动，若存在进行中的则以秒杀价格下单
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;


}
