package com.add.controller;

import com.add.controller.viewobject.ItemVO;
import com.add.error.BusinessException;
import com.add.response.CommonReturnType;
import com.add.service.ItemService;
import com.add.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller("/item")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true",allowedHeaders="*")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;


    //创建商品
    @RequestMapping(value = "/create", method={RequestMethod.POST}, consumes={CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title")String title,
                                       @RequestParam(name = "price") BigDecimal price,
                                       @RequestParam(name = "stock")Integer stock,
                                       @RequestParam(name = "description")String description,
                                       @RequestParam(name = "imgUrl")String imgUrl) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setStock(stock);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        itemModel.setPrice(price);

        ItemModel itemModelReturn = itemService.createItem(itemModel);
        ItemVO itemVo = this.convertItemVOFromItemModel(itemModelReturn);

        return CommonReturnType.create(itemVo);
    }


    //商品详情页展示
    @RequestMapping(value = "/get", method={RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id")Integer id) throws BusinessException {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVo = this.convertItemVOFromItemModel(itemModel);

        return CommonReturnType.create(itemVo);
    }

    //商品列表页面浏览
    @RequestMapping(value = "/list", method={RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem() throws BusinessException {
        List<ItemModel> itemModelList = itemService.listItem();

        List<ItemVO> itemVOList = new ArrayList<>();
        for (ItemModel it : itemModelList) {
            itemVOList.add(this.convertItemVOFromItemModel(it));
        }

        return CommonReturnType.create(itemVOList);
    }



    private ItemVO convertItemVOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVo = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVo);

        if (itemModel.getPromoModel() != null) {
            //有正在进行或者即将进行的秒杀活动
            itemVo.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVo.setPromoId(itemModel.getPromoModel().getId());
            itemVo.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            itemVo.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        } else {
            itemVo.setPromoStatus(0);
        }
        return itemVo;
    }


}
