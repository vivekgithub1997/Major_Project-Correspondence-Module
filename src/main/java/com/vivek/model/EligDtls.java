package com.vivek.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EligDtls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eligId;
	private String holderName;
	private Integer holderSsn;
	private Long caseNum;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Integer benefitAmount;
	private String denialReason;

}
