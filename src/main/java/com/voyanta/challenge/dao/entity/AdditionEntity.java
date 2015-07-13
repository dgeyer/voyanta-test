package com.voyanta.challenge.dao.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

@Entity
public class AdditionEntity {
	@Id
	@Value("idAdditionEntity")
	private String id;
	private BigDecimal result;

	public AdditionEntity() {
		super();
	}

	public AdditionEntity(String id, BigDecimal sum) {
		super();
		this.id = id;
		this.result = sum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}

}
