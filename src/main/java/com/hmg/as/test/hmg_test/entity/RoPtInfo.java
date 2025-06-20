package com.hmg.as.test.hmg_test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "T_RO_PART_INFO")
public class RoPtInfo {

	@Id
	@Column(name = "ASN_NO")
	private String asnNo;

	@Id
	@Column(name = "RO_NO")
	private String roNo;

	@Id
	@Column(name = "PT_NO")
	private String ptNo;

}
