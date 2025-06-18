package com.hmg.as.test.hmg_test.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoNoPk implements Serializable {

	@Column(name = "ASN_NO")
	private String asnNo;

	@Column(name = "RO_NO")
	private String roNo;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof RoNoPk)) {
			return false;
		}
		RoNoPk that = (RoNoPk) o;
		return Objects.equals(asnNo, that.asnNo) && Objects.equals(roNo, that.roNo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(asnNo, roNo);
	}
}