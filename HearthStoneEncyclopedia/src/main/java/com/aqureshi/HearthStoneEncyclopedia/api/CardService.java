package com.aqureshi.HearthStoneEncyclopedia.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aqureshi.HearthStoneEncyclopedia.entity.Card;
import com.aqureshi.HearthStoneEncyclopedia.entity.CardDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CardService {
	
	@Autowired
	public CardRepository cardRepository;

	public List<CardDTO> getCards() throws JsonParseException, JsonMappingException, IOException {
		ResponseEntity<String> data = cardRepository.getCards();
		
		//Extracting POJO from JSON
		//JSON structure is a map
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, List<Card>> typeToCards = objectMapper.readValue(data.getBody(), new TypeReference<Map<String, List<Card>>>(){});
		
		List<CardDTO> cards = new ArrayList<>();
		typeToCards.entrySet().forEach(e -> cards.addAll((List<CardDTO>) e.getValue().stream().map(m -> m.produceDTO()).collect(Collectors.toList())));
		
		//Only returning cards which have URL images
		return cards.stream().filter(f -> Objects.nonNull(f.getImageUrl())).collect(Collectors.toList());
	}

}
