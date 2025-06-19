package com.hmg.as.test.hmg_test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoInfoPtCnt {

    private String asnNo;

    private String roNo;

    private String vin;

    private String ptNo;

    private String ptNm;

    private Long ptCnt;

}