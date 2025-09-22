package com.wuzeyu.infrastructure.dao;

import com.wuzeyu.infrastructure.dao.po.CrowdTags;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuzeyu
 * @description 人群标签
 * @github github.com/kkkkendei
 */
@Mapper
public interface ICrowdTagsDao {

    void updateCrowdTagsStatistics(CrowdTags crowdTagsReq);

}
