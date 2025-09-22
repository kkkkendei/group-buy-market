package com.wuzeyu.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wuzeyu
 * @description 活动人群标签
 * @github github.com/kkkkendei
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TagScopeEnumVO {

    VISIBLE(true, false, "是否可看见拼团"),
    ENABLE(true, false, "是否可参与拼团"),
    ;


    private Boolean allow;
    private Boolean refuse;
    private String desc;

}
