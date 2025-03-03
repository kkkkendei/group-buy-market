package com.wuzeyu.domain.trade.adapter.port;

import com.wuzeyu.domain.trade.model.entity.NotifyTaskEntity;

/**
 * @author wuzeyu
 * @description 交易接口
 * @github github.com/kkkkendei
 */
public interface ITradePort {

    String groupBuyNotify(NotifyTaskEntity notifyTaskEntity);

}
