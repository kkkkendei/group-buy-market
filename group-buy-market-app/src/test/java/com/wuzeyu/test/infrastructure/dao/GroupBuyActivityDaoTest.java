package com.wuzeyu.test.infrastructure.dao;

import com.alibaba.fastjson2.JSON;
import com.wuzeyu.infrastructure.dao.IGroupBuyActivityDao;
import com.wuzeyu.infrastructure.dao.po.GroupBuyActivity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupBuyActivityDaoTest {

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    private Logger LOG = LoggerFactory.getLogger(GroupBuyActivityDaoTest.class);

    @Test
    public void test() {

        List<GroupBuyActivity> lists = groupBuyActivityDao.queryGroupBuyActivityList();

        LOG.info("测试结果：{}", JSON.toJSONString(lists));

    }

}
