package com.wuzeyu.api;

import com.wuzeyu.api.dto.GoodsMarketRequestDTO;
import com.wuzeyu.api.dto.GoodsMarketResponseDTO;
import com.wuzeyu.api.response.Response;

/**
 * @author wuzeyu
 * @description 营销首页服务接口
 * @github github.com/kkkkendei
 */
public interface IMarketIndexService {

    /**
     * 查询拼团营销配置
     *
     * @param goodsMarketRequestDTO 营销商品信息
     * @return 营销配置信息
     */
    Response<GoodsMarketResponseDTO> queryGroupBuyMarketConfig(GoodsMarketRequestDTO goodsMarketRequestDTO);

}
