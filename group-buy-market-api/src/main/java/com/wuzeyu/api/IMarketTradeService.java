package com.wuzeyu.api;

import com.wuzeyu.api.dto.LockMarketPayOrderRequestDTO;
import com.wuzeyu.api.dto.LockMarketPayOrderResponseDTO;
import com.wuzeyu.api.dto.SettlementMarketPayOrderRequestDTO;
import com.wuzeyu.api.dto.SettlementMarketPayOrderResponseDTO;
import com.wuzeyu.api.response.Response;

/**
 * @author wuzeyu
 * @description 营销交易服务接口
 * @github github.com/kkkkendei
 */
public interface IMarketTradeService {

    /**
     * 营销锁单
     *
     * @param requestDTO 锁单商品信息
     * @return 锁单结果信息
     */
    Response<LockMarketPayOrderResponseDTO> lockMarketPayOrder(LockMarketPayOrderRequestDTO requestDTO);

    /**
     * 营销结算
     *
     * @param requestDTO 结算商品信息
     * @return 结算结果消息
     */
    Response<SettlementMarketPayOrderResponseDTO> settlementMarketPayOrder(SettlementMarketPayOrderRequestDTO requestDTO);

}
