package com.numbersector.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.numbersector.model.InputNumbers;
import com.numbersector.service.SectorService;
import com.numbersector.util.NumberHelper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SectorController.class)
class SectorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NumberHelper numberHelper;

	@MockBean
	private SectorService sectorService;

	@Test
	void aggregateShouldReturnSuccess() throws Exception {
		String URL = "/aggregate";

		Map<String,String> mockMap = new HashMap<>();
		List<String> fakeList = new ArrayList<>();
		mockMap.put("1", "+1983236248");
		Mockito.when(numberHelper.createPrefixNumberList(Mockito.any((InputNumbers.class)))).thenReturn(mockMap);

		InputNumbers input = new InputNumbers();
		List<String> list = new ArrayList<>();
		list.add("+1983236248");
		input.setItems(list);
		String requestBody = new ObjectMapper().valueToTree(input).toString();

		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andReturn();

	}

//	@Test
//	public void aggregateShouldThrowNumberNotValidException() throws Exception {
//		String URL = "/aggregate";
//
//		Map<String,String> mockMap = new HashMap<>();
//		Mockito.when(numberHelper.createPrefixNumberList(Mockito.any((InputNumbers.class)))).thenReturn(mockMap);
//
//		InputNumbers input = new InputNumbers();
//		List<String> list = new ArrayList<>();
//		list.add("+ 1983236248");
//		input.setItems(list);
//		String requestBody = new ObjectMapper().valueToTree(input).toString();
//
//		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON).content(requestBody))
//				.andExpect(result -> assertTrue(result.getResolvedException() instanceof NumberNotValidException))
//				.andExpect(result -> assertEquals("The list contains no valid numbers",
//						result.getResolvedException().getMessage()))
//				.andReturn();
//
//	}

}