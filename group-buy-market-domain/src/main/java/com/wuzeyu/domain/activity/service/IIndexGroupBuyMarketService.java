package com.wuzeyu.domain.activity.service;

import com.wuzeyu.domain.activity.model.entity.MarketProductEntity;
import com.wuzeyu.domain.activity.model.entity.TrialBalanceEntity;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;

}
