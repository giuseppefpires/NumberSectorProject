package com.numbersector.proxy;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.numbersector.exception.SectorRequestException;

public abstract class GetRequestProxy<U> {

	@Autowired
	protected RestTemplate restTemplate;

	public ResponseEntity<U> execute(U u, String number) throws SectorRequestException {
		URI requestURI = getURI(number);
		ResponseEntity<U> tResponseEntity;
		try {
			tResponseEntity = (ResponseEntity<U>) doGet(requestURI, u);
		} catch (SectorRequestException e) {
			throw e;
		}
		return tResponseEntity;
	}

	protected ResponseEntity<?> doGet(URI requestURI, U u) throws SectorRequestException {
		ResponseEntity<?> tResponseEntity;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity entity = new HttpEntity(getHeaders());
			tResponseEntity = restTemplate.exchange(requestURI, HttpMethod.GET, entity, u.getClass());
		} catch (HttpClientErrorException e) {
			throw new SectorRequestException(e.getStatusText() + "(" + e.getRawStatusCode() + ")", e);
		} catch (Exception e) {
			throw new SectorRequestException(e.getMessage(), e);
		}
		return tResponseEntity;
	}

	protected HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return headers;
	}
	
	abstract protected URI getURI(String number) throws SectorRequestException;

}
