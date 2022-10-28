package com.vivek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CoTriggers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coTriggerId;
	private Long caseNum;
	private byte[] coPdf;
	private String trgStatus;

}
