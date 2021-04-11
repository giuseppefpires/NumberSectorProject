package com.numbersector.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.numbersector.exception.SectorRequestException;
import com.numbersector.model.SectorResponse;
import com.numbersector.proxy.GetSectorProxy;

@Service
public class SectorService {

	private final GetSectorProxy getSectorProxy;

	@Autowired
	public SectorService(GetSectorProxy getSectorProxy){
		this.getSectorProxy = getSectorProxy;
	}

	public Map<String, String> getSectors(Map<String, String> numbers)
			throws SectorRequestException{

		Map<String, String> sectors = new HashMap<>();

		for (String number : numbers.keySet()) {
			String sector = sectorLookup(number);
			sectors.put(number,sector);
			}

		return sectors;
	}

	protected String sectorLookup(String number) throws SectorRequestException {
		ResponseEntity<SectorResponse> sectorResponse = getSectorProxy.execute(new SectorResponse(), number);
		if(sectorResponse.getBody()!= null){
			return sectorResponse.getBody().getSector();
		}
		return "";
	}
}