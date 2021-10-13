package com.add.validator;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class ValidationResult {
    //校验结果
    private boolean hasErrors = false;

    //存放信息的map
    private Map<String,String> errMsgMap = new HashMap<>();

    public boolean isHasErrors() {
        return this.hasErrors;
    }

    //实现通用的格式化字符串信息获取错误信息的msg方法
    public String getErrMsg() {
        return StringUtils.join(errMsgMap.values().toArray(), ",");
    }

}
