package com.wuzeyu.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wuzeyu
 * @description 商品信息
 * @github github.com/kkkkendei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuVO {

    private String goodsId;

    private String goodsName;

    private BigDecimal originalPrice;

}
