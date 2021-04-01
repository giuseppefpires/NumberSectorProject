package com.numbersector.proxy;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.numbersector.model.SectorResponse;

@Service
public class GetSectorProxy extends GetRequestProxy<SectorResponse> {

	@Value("${talkdesk.api.base-url}")
	private String baseURL;

	@Value("${talkdesk.api.url.get.sector}")
	private String path;

	@Override
	protected URI getURI(String number) {
		StringBuilder requestURI = new StringBuilder().append(baseURL).append(path).append(number);
		return UriComponentsBuilder.fromUriString(requestURI.toString()).build().toUri();
	}
}
