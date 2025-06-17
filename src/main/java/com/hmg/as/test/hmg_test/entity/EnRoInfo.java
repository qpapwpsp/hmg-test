package com.hmg.as.test.hmg_test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="T_RO_INFO")

public class EnRoInfo {

	@Id
    @Column(name="ASN_NO", nullable = false, length = 6)
    private String asnNo;

	@Id
    @Column(name="RO_NO", nullable = false, length = 11)
    private String roNo;

    public EnRoInfo(String asnNo, String roNo) {
        this.asnNo = asnNo;
        this.roNo  = roNo;
    }

}
