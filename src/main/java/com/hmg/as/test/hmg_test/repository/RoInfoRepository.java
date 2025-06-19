package com.hmg.as.test.hmg_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hmg.as.test.hmg_test.entity.RoCrud;
import com.hmg.as.test.hmg_test.entity.RoNoPk;

@Repository
public interface RoInfoRepository extends JpaRepository<RoCrud, RoNoPk> {

}