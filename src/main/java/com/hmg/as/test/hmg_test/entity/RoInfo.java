package com.hmg.as.test.hmg_test.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "T_RO_INFO")
public class RoInfo {

    @EmbeddedId
    private RoNoPk id;

    @Column(name = "VIN", nullable = true, length = 19)
    private String vin;

    @Column(name = "PT_NO", nullable = true, length = 10, insertable = false, updatable = false)
    private String ptNo;

    @OneToOne
    @JoinColumn(name = "PT_NO")
    private PartMst partMst;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "ASN_NO", referencedColumnName = "ASN_NO"),
            @JoinColumn(name = "RO_NO", referencedColumnName = "RO_NO") })
    private List<RoPtInfo> roPtInfo;
}
