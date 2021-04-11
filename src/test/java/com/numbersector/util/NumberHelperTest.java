package com.numbersector.util;

import com.numbersector.exception.NumberNotValidException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.numbersector.model.InputNumbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NumberHelperTest {

	private static NumberHelper numberHelper;

	@BeforeAll
	public static void setUpTests() throws IOException {
		numberHelper = new NumberHelper();
	}

	/**
	 * Validate Number tests
	 */

	@Test
	public void validateNumberWithSuccess() {
		String number = "+1983236248";
		String expectedResult = "1983236248";
        String result = numberHelper.validateNumber(number);

        assertEquals(expectedResult, result);
	}

	@Test
	public void validateNumberWithInvalidNumber() {
		String number = "+1983236248A";
		String expectedResult = "";
        String result = numberHelper.validateNumber(number);

        assertEquals(expectedResult, result);	
		
	}

	@Test
	public void validateNumberWithEmptyNumber() {
		String number = "";
		String expectedResult = "";
        String result = numberHelper.validateNumber(number);

        assertEquals(expectedResult, result);	
	}

	@Test
	public void validateNumberWithNullNumber() {
		String number = null;
		String expectedResult = "";
        String result = numberHelper.validateNumber(number);

        assertEquals(expectedResult, result);	
	}

	/**
	 * Find Prefix tests
	 */

	@Test
	public void findPrefixWithSuccess() {
		String originalNumber = "+1 7490276403";
		String number = "17490276403";
		Map<String, List<String>> validNumbers = new HashMap<>();
		numberHelper.findPrefix(originalNumber, number, validNumbers);
		assertFalse(validNumbers.isEmpty());
	}
	
	@Test
	public void findPrefixWithInvalidPrefix() {
		String originalNumber = "+4 5790276403";
		String number = "45790276403";
		Map<String, List<String>> validNumbers = new HashMap<>();
		numberHelper.findPrefix(originalNumber, number, validNumbers);
		assertTrue(validNumbers.isEmpty());
		
	}

	
	/**
	 * Create Prefix Number List tests
	 */

	// e depois, pra o metodo: "createPrefixNumberList"
	
	@Test
	public void createPrefixNumberListWithSuccess() throws NumberNotValidException {
		InputNumbers input = new InputNumbers();
		List<String> items = new ArrayList<>();
		items.add("+1983236248");
		input.setItems(items);
		
		Map<String, List<String>> expectedResult = new HashMap<>();
		
		ArrayList<String> list = new ArrayList<>();
		list.add("+1983236248");
		expectedResult.put("1", list);
		
		Map<String, List<String>> result = numberHelper.createPrefixNumberList(input);
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void createPrefixNumberListWithInvalidNumber() throws NumberNotValidException {
		InputNumbers input = new InputNumbers();
		List<String> items = new ArrayList<>();
		items.add("+ 1983B6248A");
		input.setItems(items);
		
		Map<String, List<String>> expectedResult = new HashMap<String, List<String>>();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("+1983236248");
		expectedResult.put("1", list);
		
		Map<String, List<String>> result = numberHelper.createPrefixNumberList(input);
		
		assertNotEquals(expectedResult, result);
	}
	
	@Test
	public void createPrefixNumberListWithEmptyNumber() throws NumberNotValidException {
		InputNumbers input = new InputNumbers();
		List<String> items = new ArrayList<String>();
		items.add("");
		input.setItems(items);
		
		Map<String, List<String>> result = numberHelper.createPrefixNumberList(input);
		
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void createPrefixNumberListWithNullNumber() throws NumberNotValidException {
		InputNumbers input = new InputNumbers();
		List<String> items = new ArrayList<String>();
		items.add(null);
		input.setItems(items);
		
		Map<String, List<String>> result = numberHelper.createPrefixNumberList(input);
		
		assertTrue(result.isEmpty());
	}

}