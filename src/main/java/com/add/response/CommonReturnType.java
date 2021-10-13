package com.add.response;

import lombok.Data;

@Data
public class CommonReturnType {
    //status为success/fail，表示正常/异常
    private String status;
    //正常则返回json数据，错误就返回错误码
    private Object datas;

    public static CommonReturnType create(Object res) {
        return CommonReturnType.create(res, "success");
    }

    public static CommonReturnType create(Object res, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setDatas(res);
        return type;
    }

}
