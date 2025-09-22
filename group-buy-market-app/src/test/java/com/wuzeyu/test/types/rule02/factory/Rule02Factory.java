package com.wuzeyu.test.types.rule02.factory;

import com.wuzeyu.test.types.rule02.logic.RuleLogic201;
import com.wuzeyu.test.types.rule02.logic.RuleLogic202;
import com.wuzeyu.types.design.framework.link.model3.chain.BusinessLinkedList;
import com.wuzeyu.types.design.framework.link.model3.LinkArmory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Service
public class Rule02Factory {

    @Bean("demo01")
    public BusinessLinkedList<String, DynamicContext, String> demo01(RuleLogic201 ruleLogic201, RuleLogic202 ruleLogic202) {

        LinkArmory<String, DynamicContext, String> linkArmory = new LinkArmory<>("demo01", ruleLogic201, ruleLogic202);

        return linkArmory.getLogicLink();
    }

    @Bean("demo02")
    public BusinessLinkedList<String, DynamicContext, String> demo02(RuleLogic202 ruleLogic202) {

        LinkArmory<String, DynamicContext, String> linkArmory = new LinkArmory<>("demo02", ruleLogic202);

        return linkArmory.getLogicLink();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {
        private String age;
    }


}
