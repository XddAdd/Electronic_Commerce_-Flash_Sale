package com.add.service.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ToString
public class ItemModel {

    private Integer id;

    //商品名
    @NotNull(message = "商品名称不能为空")
    private String title;

    //价格
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0,message = "商品价格必须大于0")
    private BigDecimal price;

    //库存
    @NotNull(message = "库存不能不填")
    private Integer stock;

    //商品描述
    @NotNull(message = "商品描述信息不能为空")
    private String description;

    //商品的销量
    private Integer sales;

    //商品的图片链接
    @NotNull(message = "图片信息不能为空")
    private String imgUrl;


}
