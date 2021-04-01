package com.numbersector.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.numbersector.model.InputNumbers;

@Component
public class NumberHelper {

	private static final String PREFIXES_FILE_PATH = "src/main/resources/static/prefixes.txt";
	private static final String PLUS_CHAR = "+";
	private static final String SPACE_CHAR = " ";
	private static final String ZERO_PREFIX = "00";
	private static final String SPACE_REGEX = "\\s";
	private static final String NUMBERS_REGEX = "[0-9]+";
	private static final String EMPTY_STRING = "";
	private static final int THREE_CHAR_NUMBER = 3;
	private static final int LOWER_BOUNDARY_NUMBER = 6;
	private static final int HIGHER_BOUNDARY_NUMBER = 13;

	private Set<String> prefixList = new HashSet<>();

	public NumberHelper() throws IOException {
		InputStream file = new FileInputStream(PREFIXES_FILE_PATH);
		BufferedReader buf = new BufferedReader(new InputStreamReader(file));
		String line = buf.readLine();
		while (line != null) {
			this.prefixList.add(line);
			line = buf.readLine();
		}
		buf.close();
	}

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

	public void findPrefix(String originalNumber, String number, Map<String, List<String>> validNumbers) {
		List<String> list;
		for (String prefix : prefixList) {
			if (number.startsWith(prefix)) {
				if (validNumbers.containsKey(prefix)) {
					list = validNumbers.get(prefix);
					list.add(originalNumber);
					validNumbers.put(prefix, list);
				} else {
					list = new ArrayList<String>();
					list.add(originalNumber);
					validNumbers.put(prefix, list);
				}
				return;
			}
		}
	}

	public Map<String, List<String>> createPrefixNumberList(InputNumbers items) {
		Map<String, List<String>> validNumbers = new HashMap<String, List<String>>();
		for (String number : items.getItems()) {
			String reduceNumber = validateNumber(number);
			if (!reduceNumber.isEmpty()) {
				findPrefix(number, reduceNumber, validNumbers);
			}
		}
		return validNumbers;
	}
}
