package com.hmg.as.test.hmg_test.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RoNoPk implements Serializable {

	@Column(name = "ASN_NO")
	private String asnNo;

	@Column(name = "RO_NO")
	private String roNo;

}