package com.wuzeyu.infrastructure.dao;

import com.wuzeyu.infrastructure.dao.po.SCSkuActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuzeyu
 * @description 渠道商品活动配置关联表Dao
 * @github github.com/kkkkendei
 */
@Mapper
public interface ISCSkuActivityDao {

    SCSkuActivity querySCSkuActivityBySCGoodsId(SCSkuActivity scSkuActivity);

}
