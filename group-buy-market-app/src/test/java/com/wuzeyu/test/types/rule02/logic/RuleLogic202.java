package com.wuzeyu.test.types.rule02.logic;

import com.wuzeyu.test.types.rule02.factory.Rule02Factory;
import lombok.extern.slf4j.Slf4j;
import com.wuzeyu.types.design.framework.link.model3.handler.ILogicHandler;
import org.springframework.stereotype.Service;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class RuleLogic202 implements ILogicHandler<String, Rule02Factory.DynamicContext, String> {

    @Override
    public String apply(String requestParameter, Rule02Factory.DynamicContext dynamicContext) throws Exception {
        log.info("link202");
        return "link202";
    }

}
