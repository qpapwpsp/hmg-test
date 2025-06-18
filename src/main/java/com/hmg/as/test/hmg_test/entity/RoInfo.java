package com.hmg.as.test.hmg_test.entity;

import java.lang.String;
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
@Access(AccessType.FIELD)
@Table(name="T_RO_INFO")
public class RoInfo {

	@Id
	private RoNoPk id;

    @Column(name="VIN", nullable = true, length = 19)
    private String vin;

    @Column(name="PT_NO", nullable = true, length = 10)
    private String ptNo;

}
