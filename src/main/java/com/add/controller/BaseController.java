package com.add.controller;

import com.add.error.BusinessException;
import com.add.error.EmBusinessError;
import com.add.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";


    //定义exceptionHandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object hadnlerException(HttpServletRequest request, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());
            return CommonReturnType.create(responseData, "fail");
        }
        responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
        responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        return CommonReturnType.create(responseData, "fail");
    }

}
