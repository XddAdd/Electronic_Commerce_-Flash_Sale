package com.add.service.impl;

import com.add.dao.OrderDOMapper;
import com.add.dao.SequenceDoMapper;
import com.add.databoject.OrderDO;
import com.add.databoject.SequenceDo;
import com.add.error.BusinessException;
import com.add.error.EmBusinessError;
import com.add.service.ItemService;
import com.add.service.OrderService;
import com.add.service.UserService;
import com.add.service.model.ItemModel;
import com.add.service.model.OrderModel;
import com.add.service.model.UserModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Resource
    private OrderDOMapper orderDOMapper;

    @Resource
    private SequenceDoMapper sequenceDoMapper;



    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException {
        //1.校验下单状态：下单用户是否存在，是否合法，购买数量是否正确
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }

        UserModel userModel = userService.getUserById(userId);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户不存在");
        }
        if (amount <= 0 || amount > 99) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "数量信息异常");
        }

        //校验活动信息
        if (promoId != null) {
            //(1)校验对应活动是否存在这个适用商品
            if (promoId.intValue() != itemModel.getPromoModel().getId()) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动信息错误");
            } else if (itemModel.getPromoModel().getStatus() != 2){
                //(2)校验活动是否正在进行中
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动还未开始");
            }
        }

        //2.落单减库存
        boolean decrease_res = itemService.decreaseStock(itemId, amount);
        if (!decrease_res) {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        //3.订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        if (promoId != null) {
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        } else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));
        orderModel.setPromoId(promoId);

        //生成交易流水号，订单号
        orderModel.setId(this.generateOrderNo());
        OrderDO orderDO = this.convertDataObjectFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        //商品销量增加
        itemService.increaseSales(itemId, amount);

        //4.返回前端
        return orderModel;
    }

    //生成订单id
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNo(){
        //订单号有16位
        StringBuilder stringBuilder = new StringBuilder();
        //1.前8位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");

        stringBuilder.append(nowDate);

        //2.中间6位为自增序列
        //获取当前sequence
        int sequence = 0;
        SequenceDo sequenceDo = sequenceDoMapper.getSequenceByName("order_info");

        sequence = sequenceDo.getCurrentValue();
        sequenceDo.setCurrentValue(sequenceDo.getCurrentValue() + sequenceDo.getStep());
        sequenceDoMapper.updateByPrimaryKeySelective(sequenceDo);

        //前面补0
        String sequenceStr = String.valueOf(sequence);
        for (int i = 0 ; i < 6 - sequenceStr.length() ; i ++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);

        //最后两位为分库分表位
        stringBuilder.append("00");

        return stringBuilder.toString();
    }

    private OrderDO convertDataObjectFromOrderModel(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        orderDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return orderDO;
    }


}
