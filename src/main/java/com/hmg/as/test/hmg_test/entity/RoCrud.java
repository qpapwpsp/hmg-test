package com.hmg.as.test.hmg_test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_RO_INFO")
public class RoCrud {

	@EmbeddedId
	private RoNoPk id;

	@Column(name = "VIN", nullable = true, length = 19)
	private String vin;

	@Column(name = "PT_NO", nullable = true, length = 10)
	private String ptNo;

}
