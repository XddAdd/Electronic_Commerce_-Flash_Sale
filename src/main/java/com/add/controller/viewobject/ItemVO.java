package com.add.controller.viewobject;

import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ToString
public class ItemVO {

    private Integer id;

    //商品名
    private String title;

    //价格
    private BigDecimal price;

    //库存
    private Integer stock;

    //商品描述
    private String description;

    //商品的销量
    private Integer sales;

    //商品的图片链接
    private String imgUrl;



    //记录商品是否在秒杀活动中， 0表示没有秒杀活动， 1表示即将开始， 2表示秒杀活动进行中
    private Integer promoStatus;

    //秒杀活动价格
    private BigDecimal promoPrice;

    //秒杀活动ID
    private Integer promoId;

    //秒杀活动开始时间
    private String startDate;


}
