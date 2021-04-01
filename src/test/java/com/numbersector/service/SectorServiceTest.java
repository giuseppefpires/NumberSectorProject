package com.numbersector.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.numbersector.exception.NumberNullOrEmptyException;
import com.numbersector.exception.SectorRequestException;
import com.numbersector.proxy.GetSectorProxy;

public class SectorServiceTest {

	private static final String BASE_URL_VALUE = "https://challenge-business-sector-api.meza.talkdeskstg.com/";
	private static final String PATH_VALUE = "sector/";

	@Mock
	private GetSectorProxy getSectorProxy;

	@Mock
	private SectorService sectorService;

	@BeforeEach
	public void setUpTest() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(getSectorProxy, "baseURL", BASE_URL_VALUE);
		ReflectionTestUtils.setField(getSectorProxy, "path", PATH_VALUE);
	}

	@Test
	public void getSectorsWithSuccess() throws SectorRequestException, NumberNullOrEmptyException {

		Map<String, Map<String, Integer>> mockSectors = new HashMap<String, Map<String, Integer>>();
		mockSectors.put("1", new HashMap<String, Integer>());
		Mockito.when(sectorService.getSectors(Mockito.anyMap())).thenReturn(mockSectors);
		

		Map<String, List<String>> numbers = new HashMap<String, List<String>>();
		List<String> list = new ArrayList<String>();
		list.add("+1983236248");
		numbers.put("1", list);
		Map<String, Map<String, Integer>> sectors = sectorService.getSectors(numbers);
		
		assertTrue(!sectors.isEmpty());
	}
	
	

	@Test
	public void getSectorsWithEmptyNumbers() throws SectorRequestException, NumberNullOrEmptyException {
		
		Mockito.doThrow(new NumberNullOrEmptyException()).when(sectorService).getSectors(Mockito.anyMap());

		
		 Exception exception = assertThrows(NumberNullOrEmptyException.class, () -> {
		        Map<String, List<String>> numbers = new HashMap<String, List<String>>();
		        List<String> list = new ArrayList<String>();
		        list.add("");
		        numbers.put("", list);
		        Map<String, Map<String, Integer>> sectors = sectorService.getSectors(numbers);
		    });
		 
		    String expectedMessage = "Number cannot be empty";
		    String actualMessage = exception.getMessage();
		 
		    assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void getSectorsWithNullNumbers() throws SectorRequestException, NumberNullOrEmptyException {
		Mockito.doThrow(new NumberNullOrEmptyException()).when(sectorService).getSectors(Mockito.anyMap());

		
		 Exception exception = assertThrows(NumberNullOrEmptyException.class, () -> {
		        Map<String, List<String>> numbers = new HashMap<String, List<String>>();
		        
		        numbers.put(null, null);
		        Map<String, Map<String, Integer>> sectors = sectorService.getSectors(numbers);
		    });
		 
		    String expectedMessage = "Number cannot be empty";
		    String actualMessage = exception.getMessage();
		 
		    assertTrue(actualMessage.contains(expectedMessage));
	}

}
