package com.hmg.as.test.hmg_test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hmg.as.test.hmg_test.entity.RoInfoPtCnt;

@Mapper
public interface RoInfoMapper {

    List<RoInfoPtCnt> selectList(@Param("asnNo") String asnNo, @Param("page") int page);

    int test();
}