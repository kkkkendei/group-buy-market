package com.wuzeyu.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuzeyu
 * @description 回调任务实体
 * @github github.com/kkkkendei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotifyTaskEntity {

    /**
     * 拼单组队ID
     */
    private String teamId;

    /**
     * 回调接口
     */
    private String notifyUrl;

    /**
     * 回调次数
     */
    private Integer notifyCount;

    /**
     * 参数对象
     */
    private String parameterJson;

    public String lockKey() {
        return "notify_job_lock_key_" + this.teamId;
    }

}
