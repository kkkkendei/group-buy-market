package com.wuzeyu.infrastructure.dao;

import com.wuzeyu.infrastructure.dao.po.Sku;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuzeyu
 * @description 商品查询
 * @github github.com/kkkkendei
 */
@Mapper
public interface ISkuDao {

    Sku querySkuByGoodsId(String goodsId);

}
