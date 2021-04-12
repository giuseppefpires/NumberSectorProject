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
	private static PrefixListLoader prefixListLoader;

	@BeforeAll
	public static void setUpTests() throws IOException {
		numberHelper = new NumberHelper();
		prefixListLoader = new PrefixListLoader();
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
		String expectedResult = "";
        String result = numberHelper.validateNumber(null);

        assertEquals(expectedResult, result);	
	}

	/**
	 * Find Prefix tests
	 */

	@Test
	public void createPrefixNumberListWithSuccess() throws NumberNotValidException {
		InputNumbers input = new InputNumbers();
		List<String> items = new ArrayList<>();
		items.add("+1983236248");
		input.setItems(items);
		Map<String,String> expectedResult = new HashMap<>();
		expectedResult.put("+1983236248","1");
		Map<String,String> result = numberHelper.createPrefixNumberList(input);

		assertEquals(expectedResult, result);
	}
	
	@Test
	public void createPrefixNumberListWithInvalidNumber() throws NumberNotValidException {
		InputNumbers input = new InputNumbers();
		List<String> items = new ArrayList<>();
		items.add("+ 1983B6248A");
		input.setItems(items);
		Map<String,String> expectedResult = new HashMap<>();
		expectedResult.put("1", "+1983236248");
		assertThrows(NumberNotValidException.class, () -> {
			numberHelper.createPrefixNumberList(input);
		});
	}
	
	@Test
	public void createPrefixNumberListWithEmptyNumber() throws NumberNotValidException {
		InputNumbers input = new InputNumbers();
		List<String> items = new ArrayList<>();
		items.add("");
		input.setItems(items);

		assertThrows(NumberNotValidException.class, () -> {
			numberHelper.createPrefixNumberList(input);
		});
	}
	
	@Test
	public void createPrefixNumberListWithNullNumber() throws NumberNotValidException {
		InputNumbers input = new InputNumbers();
		List<String> items = new ArrayList<>();
		items.add(null);
		input.setItems(items);
		assertThrows(NumberNotValidException.class, () -> {
			numberHelper.createPrefixNumberList(input);
		});
	}
}