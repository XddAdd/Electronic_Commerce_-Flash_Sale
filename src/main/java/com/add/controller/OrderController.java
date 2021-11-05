package com.add.controller;

import com.add.controller.viewobject.ItemVO;
import com.add.error.BusinessException;
import com.add.error.EmBusinessError;
import com.add.response.CommonReturnType;
import com.add.service.OrderService;
import com.add.service.model.ItemModel;
import com.add.service.model.OrderModel;
import com.add.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller("/order")
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",allowedHeaders="*")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;



    //下单请求
    @RequestMapping(value = "/createorder", method={RequestMethod.POST}, consumes={CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId")Integer itemId,
                                       @RequestParam(name = "amount") Integer amount) throws BusinessException {
        //获取用户登录信息
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || isLogin == false) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");


        OrderModel orderModel = orderService.createOrder(userModel.getId(), itemId, amount);

        return CommonReturnType.create(null);
    }


}
