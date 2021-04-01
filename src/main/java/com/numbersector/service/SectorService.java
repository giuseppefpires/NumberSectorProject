package com.numbersector.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.numbersector.exception.NumberNullOrEmptyException;
import com.numbersector.exception.SectorRequestException;
import com.numbersector.model.SectorResponse;
import com.numbersector.proxy.GetSectorProxy;

@Service
public class SectorService {

	@Autowired
	private GetSectorProxy getSectorProxy;
	
	public Map<String, Map<String, Integer>> getSectors(Map<String, List<String>> numbers)
			throws SectorRequestException, NumberNullOrEmptyException {
		
		if(numbers == null || numbers.isEmpty()) {
			throw new NumberNullOrEmptyException("Number cannot be empty");
		}
		
		Map<String, Map<String, Integer>> sectors = new TreeMap<>();

		for (String pref : numbers.keySet()) {
			List<String> numberList = numbers.get(pref);
			Map<String, Integer> sector = new TreeMap<>();

			for (String number : numberList) {
				sectorLookup(number, sector);
			}
			sectors.put(pref, sector);
		}
		return sectors;
	}

	protected void sectorLookup(String number, Map<String, Integer> sector) throws SectorRequestException {
		ResponseEntity<SectorResponse> sectorResponse = getSectorProxy.execute(new SectorResponse(), number);
		SectorResponse response = sectorResponse.getBody();
		sector.put(response.getSector(), sector.getOrDefault(response.getSector(), 0) + 1);
	}
}