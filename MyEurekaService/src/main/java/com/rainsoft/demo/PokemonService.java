package com.rainsoft.demo;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonService {
	
	private final static Logger logger = LoggerFactory.getLogger(PokemonService.class);
	
	private static final Hashtable<String,Pokemon> pokeDb = new Hashtable<>();
	
	
	@RequestMapping(path="/pokemon",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Pokemon> updatePokemon(@RequestBody Pokemon poke) {
		logger.info("Input parameter:", poke);
		Map<String,Pokemon> result = new HashMap<>();
		result.put("Hello there!" , poke);
		pokeDb.put(poke.getId(), poke);
		return result; 
	}
	
	@RequestMapping(path="/pokemon/{id}",method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Pokemon queryPokemon(@PathVariable("id") String id) {
		logger.info("path variable:" +id);
		logger.info("get pokemon:" + pokeDb.get(id));
		
		return pokeDb.get(id);
	}
	
}
