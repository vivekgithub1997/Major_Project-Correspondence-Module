package com.vivek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.model.CoTriggers;

public interface CoTriggerRepo extends JpaRepository<CoTriggers, Integer> {
	
	public CoTriggers findByCaseNum(Long caseNum);
	

}
