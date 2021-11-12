package com.add.service.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class UserModel implements Serializable {
    private Integer id;

    @NotBlank(message="用户名不能为空")
    private String name;

    @NotNull(message="性别不能不填")
    private Byte gender;

    @NotNull(message="年龄不能不填")
    @Min(value=0, message="年龄必须大于0岁")
    @Max(value=150, message="年龄必须小于150岁")
    private Integer age;

    @NotBlank(message="手机号不能为空")
    private String telephone;
    private String registerMode;
    private String thirdPartyId;

    @NotBlank(message="密码不能为空")
    private String encrptPassword;




}
