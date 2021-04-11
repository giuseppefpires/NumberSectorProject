package com.numbersector.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.numbersector.exception.NumberNotValidException;
import com.numbersector.exception.SectorRequestException;
import com.numbersector.model.InputNumbers;
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
			throws NumberNotValidException, SectorRequestException{

		Map<String,String> numbersMap = numberHelper.createPrefixNumberList(items);
		Map<String,String> sectorsMap = sectorService.getSectors(numbersMap);

		return numberHelper.mountResponse(numbersMap,sectorsMap);
	}
}
