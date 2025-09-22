package com.wuzeyu.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wuzeyu
 * @description 回调请求对象
 * @github github.com/kkkkendei
 */
@Data
public class NotifyRequestDTO {

    /** 组队ID */
    private String teamId;

    /** 外部单号 */
    private List<String> outTradeNoList;

}
