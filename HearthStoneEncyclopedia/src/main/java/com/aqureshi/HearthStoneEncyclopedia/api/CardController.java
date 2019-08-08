package com.aqureshi.HearthStoneEncyclopedia.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aqureshi.HearthStoneEncyclopedia.entity.CardDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class CardController {

	@Autowired
	private CardService cardService;
	
	@GetMapping("/api/cards")
	public List<CardDTO> getCards() throws JsonParseException, JsonMappingException, IOException {
		return cardService.getCards();
	}
	
}
