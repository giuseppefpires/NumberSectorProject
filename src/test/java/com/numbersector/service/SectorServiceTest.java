package com.numbersector.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import com.numbersector.exception.SectorRequestException;
import com.numbersector.proxy.GetSectorProxy;

class SectorServiceTest {

	private static final String BASE_URL_VALUE = "https://challenge-business-sector-api.meza.talkdeskstg.com/";
	private static final String PATH_VALUE = "sector/";

	@Mock
	private GetSectorProxy getSectorProxy;

	@Mock
	private SectorService sectorService;

	@BeforeEach
	void setUpTest() {
		ReflectionTestUtils.setField(getSectorProxy, "baseURL", BASE_URL_VALUE);
		ReflectionTestUtils.setField(getSectorProxy, "path", PATH_VALUE);
	}

//	@Test
//	void getSectorsWithSuccess() throws SectorRequestException{
//
//		Map<String, String> mockSectors = new HashMap<>();
//		mockSectors.put("1", "");
//		Mockito.when(sectorService.getSectors(Mockito.anyMap())).thenReturn(mockSectors);
//
//
//		Map<String, String> numbers = new HashMap<>();
//		numbers.put("1", "+1983236248");
//		Map<String,String> sectors = sectorService.getSectors(numbers);
//
//		assertFalse(sectors.isEmpty());
//	}
}
