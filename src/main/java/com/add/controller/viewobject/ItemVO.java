package com.add.controller.viewobject;

import lombok.Data;
import lombok.ToString;

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


}
