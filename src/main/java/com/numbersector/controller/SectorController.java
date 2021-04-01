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

	@Autowired
	private NumberHelper numberHelper;

	@Autowired
	private SectorService sectorService;

	@PostMapping("/aggregate")
	public Map<String, Map<String, Integer>> aggregate(@RequestBody InputNumbers items)
			throws NumberNotValidException, SectorRequestException, NumberNullOrEmptyException {
		Map<String, List<String>> numbersList = numberHelper.createPrefixNumberList(items);
		if (numbersList.isEmpty()) {
			throw new NumberNotValidException("The list contains no valid numbers");
		}
		return sectorService.getSectors(numbersList);
	}

	@ExceptionHandler
	public ResponseEntity<SectorErrorResponse> handleNumberNotValidException(NumberNotValidException ex) {
		SectorErrorResponse error = new SectorErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<SectorErrorResponse> handleSectorRequestException(SectorRequestException ex) {
		SectorErrorResponse error = new SectorErrorResponse();
		error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
