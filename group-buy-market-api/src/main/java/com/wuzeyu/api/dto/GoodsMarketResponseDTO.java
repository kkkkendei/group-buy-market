package com.wuzeyu.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzeyu
 * @description 商品营销应答对象
 * @github github.com/kkkkendei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsMarketResponseDTO {

    // 活动ID
    private Long activityId;

    // 商品信息
    private Goods goods;

    // 组队信息（1个个人的置顶、2个随机的「获取10个，随机取2个」）
    private List<Team> teamList;

    // 组队统计
    private TeamStatistic teamStatistic;

    /**
     * 商品信息
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Goods {
        // 商品ID
        private String goodsId;

        // 原始价格
        private BigDecimal originalPrice;

        // 折扣金额
        private BigDecimal deductionPrice;

        // 支付价格
        private BigDecimal payPrice;
    }

    /**
     * 组队信息
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Team {

        // 用户ID
        private String userId;

        // 拼单组队ID
        private String teamId;

        // 活动ID
        private Long activityId;

        // 目标数量
        private Integer targetCount;

        // 完成数量
        private Integer completeCount;

        // 锁单数量
        private Integer lockCount;

        // 拼团开始时间 - 参与拼团时间
        private Date validStartTime;

        // 拼团结束时间 - 拼团有效时长
        private Date validEndTime;

        // 倒计时(字符串) validEndTime - validStartTime
        private String validTimeCountdown;

        /** 外部交易单号-确保外部调用唯一幂等 */
        private String outTradeNo;

        public static String differenceDateTime2Str(Date validStartTime, Date validEndTime) {
            if (validStartTime == null || validEndTime == null) {
                return "无效的时间";
            }

            long diffInMilliseconds = validEndTime.getTime() - validStartTime.getTime();

            if (diffInMilliseconds < 0) {
                return "已结束";
            }

            long seconds = TimeUnit.MILLISECONDS.toSeconds(diffInMilliseconds) % 60;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds) % 60;
            long hours = TimeUnit.MILLISECONDS.toHours(diffInMilliseconds) % 24;
            long days = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);

            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

    }

    /**
     * 组队统计
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TeamStatistic {

        // 开团队伍数量
        private Integer allTeamCount;

        // 成团队伍数量
        private Integer allTeamCompleteCount;

        // 参团人数总量 - 一个商品的总参团人数
        private Integer allTeamUserCount;

    }

}
