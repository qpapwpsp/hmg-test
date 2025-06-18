package com.hmg.as.test.hmg_test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="T_GEN_RO_INFO")
public class GenRoInfo {

	@Id
    @Column(name="ASN_NO", nullable = false, length = 6)
    private String asnNo;

	@Id
    @Column(name="RO_NO", nullable = false, length = 13)
    private String roNo;

    @Column(name="VIN", nullable = true, length = 19)
    private String vin;

    @Column(name="PT_NO", nullable = true, length = 10)
    private String ptNo;

}
