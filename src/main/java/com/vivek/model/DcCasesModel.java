package com.vivek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DcCasesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long caseNum;
	private Integer appId;
	private Integer planId;

}
