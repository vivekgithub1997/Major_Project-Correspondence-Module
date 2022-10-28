package com.vivek.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class CitizenApplicationModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appId;
	private String fullName;
	private String email;
	private long phoneNum;
	private Integer Ssn;
	private String gender;
	private LocalDate dob;
	private String stateName;
	@CreationTimestamp
	@Column(name = "created_Date", updatable = false)
	private LocalDate createDate;
	@CreationTimestamp
	@Column(name = "updated_Date", insertable = false)
	private LocalDate updateDate;
	private String createdBy;
	private String updatedBy;

}
