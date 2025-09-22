package com.wuzeyu.domain.trade.model.entity;

import com.wuzeyu.types.enums.GroupBuyOrderEnumVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wuzeyu
 * @description 拼团组队实体对象
 * @github github.com/kkkkendei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupBuyTeamEntity {

    /** 拼单组队ID */
    private String teamId;

    /** 活动ID */
    private Long activityId;

    /** 目标数量 */
    private Integer targetCount;

    /** 完成数量 */
    private Integer completeCount;

    /** 锁单数量 */
    private Integer lockCount;

    /** 状态（0-拼单中、1-完成、2-失败） */
    private GroupBuyOrderEnumVO status;

    /** 拼团开始时间 - 参与拼团时间 */
    private Date validStartTime;

    /** 拼团结束时间 - 拼团有效时长 */
    private Date validEndTime;

    /** 回调地址 */
    private String notifyUrl;

}
