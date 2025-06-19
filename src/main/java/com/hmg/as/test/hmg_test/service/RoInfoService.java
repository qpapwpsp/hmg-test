package com.hmg.as.test.hmg_test.service;

import java.util.List;

import com.hmg.as.test.hmg_test.entity.RoCrud;
import com.hmg.as.test.hmg_test.entity.RoInfo;
import com.hmg.as.test.hmg_test.entity.RoInfoPtCnt;
import com.hmg.as.test.hmg_test.entity.RoNoPk;

public interface RoInfoService {

    RoInfo getByRoInfo(String asnNo, String roNo);

    List<RoInfo> getByRoInfoKeyList(List<RoNoPk> roNoPkList);

    List<RoInfo> getList(String asnNo, int page);
    
    List<RoInfo> getListMybatis(String asnNo, int page);

    RoCrud insert(RoCrud roCrud);

    RoCrud update(RoCrud roCrud);

    RoCrud updateByColumn(RoCrud roCrud);

    String delete(RoNoPk roNoPk);

    List<RoInfoPtCnt> getRoListPtCnt(String asnNo);

}
