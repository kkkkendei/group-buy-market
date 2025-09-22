package com.wuzeyu.infrastructure.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wuzeyu
 * @description 商品信息
 * @github github.com/kkkkendei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    /** 自增 */
    private Long id;

    /** 来源 */
    private String source;

    /** 渠道 */
    private String channel;

    /** 商品ID */
    private String goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 原始价格 */
    private BigDecimal originalPrice;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

}
