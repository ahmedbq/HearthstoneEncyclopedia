package com.aqureshi.HearthStoneEncyclopedia.api;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CardRepository {

	public ResponseEntity<String> getCards() {
		final String uri = "https://omgvamp-hearthstone-v1.p.rapidapi.com/cards/";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("x-rapidapi-host", "omgvamp-hearthstone-v1.p.rapidapi.com");
		headers.add("x-rapidapi-key", "c0f4827ff0msh92243e80d488e33p191b38jsnb2147971a15a");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	}

}
