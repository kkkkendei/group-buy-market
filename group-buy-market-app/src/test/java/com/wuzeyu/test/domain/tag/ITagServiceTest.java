package com.wuzeyu.test.domain.tag;

import com.wuzeyu.domain.tag.service.TagService;
import com.wuzeyu.infrastructure.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBitSet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description 人群标签服务测试
 * @github github.com/kkkkendei
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITagServiceTest {

    @Resource
    private TagService tagService;
    @Resource
    private IRedisService redisService;

    @Test
    public void test_tag_job() {
        tagService.execTagBatchJob("RQ_KJHKL98UU78H66554GFDV", "10001");
    }

    @Test
    public void test_get_tag_bitmap() {
        RBitSet bitSet = redisService.getBitSet("RQ_KJHKL98UU78H66554GFDV");
        // 是否存在
        log.info("w 存在，预期结果为 true，测试结果:{}", bitSet.get(redisService.getIndexFromUserId("w")));
        log.info("gudebai 不存在，预期结果为 false，测试结果:{}", bitSet.get(redisService.getIndexFromUserId("gudebai")));
    }

    @Test
    public void test_null_tag_bitmap() {
        RBitSet bitSet = redisService.getBitSet("null");
        log.info("测试结果:{}", bitSet.isExists());
    }


}
