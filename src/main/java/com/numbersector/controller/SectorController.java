package com.numbersector.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.numbersector.exception.NumberNotValidException;
import com.numbersector.exception.NumberNullOrEmptyException;
import com.numbersector.exception.SectorRequestException;
import com.numbersector.model.InputNumbers;
import com.numbersector.model.SectorErrorResponse;
import com.numbersector.service.SectorService;
import com.numbersector.util.NumberHelper;

@RestController
public class SectorController {

	private final NumberHelper numberHelper;
	private final SectorService sectorService;

	public SectorController(NumberHelper numberHelper, SectorService sectorService){
		this.numberHelper = numberHelper;
		this.sectorService = sectorService;
	}

	@PostMapping("/aggregate")
	public Map<String, Map<String, Integer>> aggregate(@RequestBody InputNumbers items)
			throws NumberNotValidException, SectorRequestException, NumberNullOrEmptyException {
		Map<String, List<String>> numbersList = numberHelper.createPrefixNumberList(items);
		return sectorService.getSectors(numbersList);
	}
}
