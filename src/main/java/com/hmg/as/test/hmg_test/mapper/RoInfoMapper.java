package com.hmg.as.test.hmg_test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hmg.as.test.hmg_test.entity.RoInfo;

@Mapper
public interface RoInfoMapper {

    List<RoInfo> selectList(@Param("asnNo") String asnNo, @Param("page") int page);

}