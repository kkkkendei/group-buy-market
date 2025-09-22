package com.wuzeyu.api;

import com.wuzeyu.api.response.Response;

/**
 * @author wuzeyu
 * @description DCC动态配置中心
 * @github github.com/kkkkendei
 */
public interface IDCCService {

    Response<Boolean> updateConfig(String key, String value);

}
