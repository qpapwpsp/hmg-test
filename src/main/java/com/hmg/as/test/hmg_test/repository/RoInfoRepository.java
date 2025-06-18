package com.hmg.as.test.hmg_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hmg.as.test.hmg_test.entity.RoInfo;
import com.hmg.as.test.hmg_test.entity.RoNoPk;

public interface RoInfoRepository extends JpaRepository<RoInfo, RoNoPk> {

}