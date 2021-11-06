package com.add.service.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

//用户下单的交易模型
@Data
@ToString
public class OrderModel {
    private String id;

    //购买的用户id
    private Integer userId;

    //购买的商品id
    private Integer itemId;

    //秒杀活动,若promoId非空，则是秒杀方式下单
    private Integer promoId;

    //购买商品的价格,若promoId非空，则是秒杀方式下单
    private BigDecimal itemPrice;

    //购买数量
    private Integer amount;

    //购买金额,若promoId非空，则是秒杀方式下单
    private BigDecimal orderPrice;
}
