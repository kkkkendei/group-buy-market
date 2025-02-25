package com.wuzeyu.domain.activity.model.valobj;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author wuzeyu
 * @description 商品信息
 * @github github.com/kkkkendei
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuVO {

    private String goodsId;

    private String goodsName;

    private BigDecimal originalPrice;

}
