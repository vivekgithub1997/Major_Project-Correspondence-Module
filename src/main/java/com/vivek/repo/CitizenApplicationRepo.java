package com.vivek.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivek.model.CitizenApplicationModel;

public interface CitizenApplicationRepo extends JpaRepository<CitizenApplicationModel, Integer> {

}
