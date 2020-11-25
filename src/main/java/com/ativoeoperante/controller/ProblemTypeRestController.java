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

import com.ativoeoperante.dal.DALAdmin;
import com.ativoeoperante.dal.DALProblemType;
import com.ativoeoperante.dal.DALUser;
import com.ativoeoperante.model.ProblemType;

@RestController
@RequestMapping("/problem-types")
@CrossOrigin(origins="*")
public class ProblemTypeRestController {
	private DALProblemType dalProblemType = new DALProblemType();
	private DALUser dalUser = new DALUser();
	private DALAdmin dalAdmin = new DALAdmin();
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getProblemTypes(@RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			HashMap<String, ProblemType> map = new HashMap<>();
			ArrayList<ProblemType> problemTypes = dalProblemType.get("");
			
			for(ProblemType problemType : problemTypes) {
				map.put(problemType.getId()+"", problemType);
			}
			
			return new ResponseEntity<Object>(map.values(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getProblemType(@PathVariable String id, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			HashMap<String, Object> map = new HashMap<>();
			ProblemType problemType = dalProblemType.get(Integer.parseInt(id));
				
			map.put(problemType.getId()+"", problemType);
			
			return new ResponseEntity<Object>(map.values(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createProblemType(@RequestParam String name, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			ProblemType problemType = new ProblemType(name);
			
			if(dalProblemType.insert(problemType)) {
				return "{\"status\": true}";
			}
			return "{\"status\": false}";
		}
		
		return "{\"status\": false}";
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public String changeProblemType(@RequestParam String id, @RequestParam String name, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			ProblemType problemType = new ProblemType(Integer.parseInt(id), name);
			if(dalProblemType.update(problemType)) {
				return "{\"status\": true}";
			}
			return "{\"status\": false}";
		}
		
		return "{\"status\": false}";
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public String deleteProblemType(@RequestParam String id, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			if(dalProblemType.delete(Integer.parseInt(id))) {
				return "{\"status\": true}";
			}
			return "{\"status\": false}";
		}
		
		return "{\"status\": false}";
	}
}
