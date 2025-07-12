package com.wuzeyu.test.types.rule02.test;

import com.alibaba.fastjson.JSON;
import com.wuzeyu.test.types.rule02.factory.Rule02Factory;
import com.wuzeyu.types.design.framework.link.model3.chain.BusinessLinkedList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Rule02Test {

    @Resource(name = "demo01")
    private BusinessLinkedList<String, Rule02Factory.DynamicContext, String> businessLinkedList01;

    @Resource(name = "demo02")
    private BusinessLinkedList<String, Rule02Factory.DynamicContext, String> businessLinkedList02;

    @Test
    public void test_model02_01() throws Exception {
        String apply = businessLinkedList01.apply("123", new Rule02Factory.DynamicContext());
        log.info("测试结果:{}", JSON.toJSONString(apply));
    }

    @Test
    public void test_model02_02() throws Exception {
        String apply = businessLinkedList02.apply("123", new Rule02Factory.DynamicContext());
        log.info("测试结果:{}", JSON.toJSONString(apply));
    }

}