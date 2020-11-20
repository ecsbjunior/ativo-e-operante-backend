package com.ativoeoperante.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ativoeoperante.dal.DALCompetentOrgan;
import com.ativoeoperante.model.CompetentOrgan;

@RestController
@RequestMapping("/competent-organs")
@CrossOrigin(origins="*")
public class CompetentOrganRestController {
	private DALCompetentOrgan dalCompetentOrgan = new DALCompetentOrgan();
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getCompetentOrgans() {
		HashMap<String, CompetentOrgan> map = new HashMap<>();
		ArrayList<CompetentOrgan> competentOrgans = dalCompetentOrgan.get("");
		
		for(CompetentOrgan competentOrgan : competentOrgans) {
			map.put(competentOrgan.getId()+"", competentOrgan);
		}
		
		return new ResponseEntity<Object>(map.values(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getCompetentOrgan(@PathVariable String id) {
		HashMap<String, CompetentOrgan> map = new HashMap<>();
		CompetentOrgan competentOrgan = dalCompetentOrgan.get(Integer.parseInt(id));
		
		map.put(competentOrgan.getId()+"", competentOrgan);
		
		return new ResponseEntity<Object>(map.values(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createCompetentOrgan(@RequestParam String name) {
		CompetentOrgan competentOrgan = new CompetentOrgan(name);
		if(dalCompetentOrgan.insert(competentOrgan)) {
			return "{\"status\": true}";
		}
		return "{\"status\": false}";
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public String changeCompetentOrgan(@RequestParam String id, @RequestParam String name) {
		CompetentOrgan competentOrgan = new CompetentOrgan(Integer.parseInt(id), name);
		if(dalCompetentOrgan.update(competentOrgan)) {
			return "{\"status\": true}";
		}
		return "{\"status\": false}";
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public String deleteCompetentOrgan(@RequestParam String id) {
		if(dalCompetentOrgan.delete(Integer.parseInt(id))) {
			return "{\"status\": true}";
		}
		return "{\"status\": false}";
	}
}
