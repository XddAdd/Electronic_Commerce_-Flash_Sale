package com.add.service.impl;

import com.add.dao.PromoDOMapper;
import com.add.databoject.PromoDO;
import com.add.service.PromoService;
import com.add.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class PromoServiceImpl implements PromoService {

    @Resource
    private PromoDOMapper promoDOMapper;





    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        //获取对应商品秒杀活动信息
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);

        //dataObject->model
        PromoModel promoModel = this.convertPromoModelFromPromoDO(promoDO);
        if (promoModel == null) {
            return null;
        }

        //判断当前时间是否秒杀活动即将开始或者正在进行
        if (promoModel.getStartDate().isAfterNow()) {
            promoModel.setStatus(1); //未开始
        } else if (promoModel.getEndDate().isBeforeNow()) {
            promoModel.setStatus(3); //已经结束
        } else {
            promoModel.setStatus(2); //正在进行
        }


        return promoModel;
    }

    private PromoModel convertPromoModelFromPromoDO(PromoDO promoDO) {
        if (promoDO == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);

        promoModel.setPromoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));
        return promoModel;
    }
}
