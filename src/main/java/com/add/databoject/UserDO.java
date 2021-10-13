package com.add.databoject;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDO {

    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telephone;
    private String registerMode;
    private String thirdPartyId;




}