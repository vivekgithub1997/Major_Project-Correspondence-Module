package com.vivek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.model.DcCasesModel;

public interface DcCasesRepo extends JpaRepository<DcCasesModel, Long> {

}
