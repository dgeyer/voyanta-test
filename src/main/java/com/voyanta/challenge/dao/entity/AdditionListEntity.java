package com.voyanta.challenge.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdditionListEntity {

	@Id
	Long id;
	@Column(nullable = false)
	Integer httpStatus;

	public AdditionListEntity() {
		super();
	}

	public AdditionListEntity(Long id, Integer httpStatus) {
		super();
		this.id = id;
		this.httpStatus = httpStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
}
