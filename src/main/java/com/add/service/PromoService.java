package com.add.service;

import com.add.service.model.PromoModel;

public interface PromoService {

    //根据itemId获取即将进行的或者正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);


}
