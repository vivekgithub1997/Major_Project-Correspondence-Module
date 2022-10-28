package com.vivek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.model.EligDtls;

public interface EligDtlsRepo extends JpaRepository<EligDtls, Integer> {

	public EligDtls findByCaseNum(Long caseNum);

}
