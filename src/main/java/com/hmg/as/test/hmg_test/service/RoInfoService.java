package com.hmg.as.test.hmg_test.service;

import java.util.List;

import com.hmg.as.test.hmg_test.entity.RoInfo;

public interface RoInfoService {
	RoInfo getByRoInfo(String asnNo, String roNo);

	List<RoInfo> getList(String asnNo, int page);
}
