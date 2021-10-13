package com.add.service;

import com.add.error.BusinessException;
import com.add.service.model.UserModel;


public interface UserService {

    //通过id获取用户对象
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    /*
    telephone:用户注册手机
    password:用户加密后的密码
     */
    UserModel validateLogin(String telephone, String encrptPassword) throws BusinessException;

}
