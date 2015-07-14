package com.voyanta.challenge.dao.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AdditionEntity {
	@Id
	private String id;
	// Adding Scale y precision to max values in MySQL, could change for other
	// database
	@Column(precision = 65, scale = 30)
	private BigDecimal result;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIST_ID", nullable = false)
	AdditionListEntity additionListEntity;

	public AdditionEntity() {
		super();
	}

	public AdditionEntity(String id, BigDecimal sum) {
		super();
		this.id = id;
		this.result = sum;
	}

	public AdditionEntity(String id, BigDecimal sum, AdditionListEntity list) {
		super();
		this.id = id;
		this.result = sum;
		this.additionListEntity = list;
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

	public AdditionListEntity getAdditionListEntity() {
		return additionListEntity;
	}

	public void setAdditionListEntity(AdditionListEntity additionListEntity) {
		this.additionListEntity = additionListEntity;
	}

}
