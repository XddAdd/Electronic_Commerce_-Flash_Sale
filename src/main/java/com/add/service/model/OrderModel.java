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

    //购买商品的价格
    private BigDecimal itemPrice;

    //购买数量
    private Integer amount;

    //购买金额
    private BigDecimal orderPrice;
}
