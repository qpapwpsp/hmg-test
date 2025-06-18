package com.hmg.as.test.hmg_test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="T_PART_MST")
public class PartMst {

	@Id
    @Column(name="PT_NO", nullable = false, length = 10)
    private String ptNo;

	@Column(name="PT_NM", nullable = true, length = 100)
    private String ptNm;
}
