package com.wuzeyu.domain.activity.service;

import com.wuzeyu.domain.activity.model.entity.MarketProductEntity;
import com.wuzeyu.domain.activity.model.entity.TrialBalanceEntity;

/**
 * @author wuzeyu
 * @description 首页营销服务接口
 * @github github.com/kkkkendei
 */
public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;

}
