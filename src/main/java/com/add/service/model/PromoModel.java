package com.add.service.model;

import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Data
@ToString
public class PromoModel {
    private Integer id;

    //秒杀活动名称
    private String promoName;

    //秒杀活动的开始时间
    private DateTime startDate;

    //秒杀活动的商品
    private Integer itemId;

    //秒杀活动的商品价格
    private BigDecimal promoItemPrice;


}
