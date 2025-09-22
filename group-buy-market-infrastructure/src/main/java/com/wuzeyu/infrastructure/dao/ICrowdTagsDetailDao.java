package com.wuzeyu.infrastructure.dao;

import com.wuzeyu.infrastructure.dao.po.CrowdTagsDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuzeyu
 * @description 人群标签明细
 * @github github.com/kkkkendei
 */
@Mapper
public interface ICrowdTagsDetailDao {

    void addCrowdTagsUserId(CrowdTagsDetail crowdTagsDetailReq);

}
