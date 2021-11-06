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

    //秒杀活动的结束时间
    private DateTime endDate;

    //秒杀活动的商品
    private Integer itemId;

    //秒杀活动状态
    private Integer status; // 1表示未开始，2表示进行中，3表示已经结束

    //秒杀活动的商品价格
    private BigDecimal promoItemPrice;


}
