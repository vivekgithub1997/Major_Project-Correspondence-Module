package com.vivek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.model.CoTriggers;
import com.vivek.service.CoTrgService;

@RestController
public class CorrespondenceController {

	@Autowired
	private CoTrgService trgService;

	@GetMapping("/coTrg/{caseNum}")
	public ResponseEntity<CoTriggers> CoTrg(@PathVariable Long caseNum) throws Exception {

		CoTriggers coTriggers = trgService.correspondenceTrg(caseNum);
		return new ResponseEntity<>(coTriggers, HttpStatus.OK);

	}

}
