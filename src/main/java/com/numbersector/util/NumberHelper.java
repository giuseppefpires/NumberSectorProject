package com.numbersector.util;

import java.util.HashMap;
import java.util.Map;
import com.numbersector.exception.NumberNotValidException;
import org.springframework.stereotype.Component;
import com.numbersector.model.InputNumbers;

@Component
public class NumberHelper {


	private static final String PLUS_CHAR = "+";
	private static final String SPACE_CHAR = " ";
	private static final String ZERO_PREFIX = "00";
	private static final String SPACE_REGEX = "\\s";
	private static final String NUMBERS_REGEX = "[0-9]+";
	private static final String EMPTY_STRING = "";
	private static final int THREE_CHAR_NUMBER = 3;
	private static final int LOWER_BOUNDARY_NUMBER = 6;
	private static final int HIGHER_BOUNDARY_NUMBER = 13;

	public String validateNumber(String number) {
		if(number == null) {
			return EMPTY_STRING;
		}
		if (number.startsWith(PLUS_CHAR)) {
			number = number.substring(1);
		} else if (number.startsWith(ZERO_PREFIX)) {
			number = number.substring(2);
		}
		if (number.startsWith(SPACE_CHAR)) {
			return EMPTY_STRING;
		}
		// remove whitespaces
		number = number.replaceAll(SPACE_REGEX, EMPTY_STRING);

		if (number.matches(NUMBERS_REGEX)) {
			if (number.length() == THREE_CHAR_NUMBER
					|| (number.length() > LOWER_BOUNDARY_NUMBER && number.length() < HIGHER_BOUNDARY_NUMBER)) {
				return number;
			}
		}
		return EMPTY_STRING;
	}

	public void findPrefix(String originalNumber, String reduceNumber, Map<String, String> validNumbers) {
		for (String prefix :PrefixListLoader.prefixList ) {
			if (reduceNumber.startsWith(prefix)) {
				validNumbers.put(originalNumber,prefix);
			}
		}
	}

	public Map<String, String> createPrefixNumberList(InputNumbers items) throws NumberNotValidException {
		Map<String, String> validNumbers = new HashMap<>();
		for (String originalNumber : items.getItems()) {
			String reduceNumber = validateNumber(originalNumber);
			if (!reduceNumber.isEmpty()) {
				findPrefix(originalNumber, reduceNumber, validNumbers);
			}
		}
		if(validNumbers.isEmpty()){
			throw new NumberNotValidException("The list contains no valid numbers");
		}
		return validNumbers;
	}

	public Map<String, Map<String, Integer>> mountResponse(Map<String,String> numbersMap, Map<String,String> sectorsMap){

		Map<String, Map<String, Integer>> response = new HashMap<>();

		for (String number : numbersMap.keySet()){
			String prefix = numbersMap.get(number);
			String sector = sectorsMap.get(number);
			Map<String, Integer> sectors = new HashMap<>();
			sectors.put(sector, sectors.getOrDefault(sector,0)+ 1);
			response.put(prefix, sectors);
		}

		return response;
	}
}
