package com.wuzeyu.infrastructure.dao;

import com.wuzeyu.infrastructure.dao.po.CrowdTagsJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuzeyu
 * @description 人群标签任务
 * @github github.com/kkkkendei
 */
@Mapper
public interface ICrowdTagsJobDao {

    CrowdTagsJob queryCrowdTagsJob(CrowdTagsJob crowdTagsJobReq);

}
