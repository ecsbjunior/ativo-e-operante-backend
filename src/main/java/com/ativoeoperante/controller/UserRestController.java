package com.ativoeoperante.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ativoeoperante.dal.DALAdmin;
import com.ativoeoperante.dal.DALUser;
import com.ativoeoperante.model.User;
import com.ativoeoperante.util.Conexao;
import com.ativoeoperante.util.HashGenerator;

@RestController
@RequestMapping(value="/users")
@CrossOrigin(origins="*")
public class UserRestController {
	private DALUser dalUser = new DALUser();
	private DALAdmin dalAdmin = new DALAdmin();
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity <Object> getUsers(@RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			HashMap<String, Object> map = new HashMap<>();
			
			ArrayList<User> users = dalUser.get("");
			
			for(User user : users)
				map.put(user.getId()+"", user);
			
			return new ResponseEntity<>(map.values(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{cpf}", method=RequestMethod.GET)
	public ResponseEntity <Object> getUser(@PathVariable String cpf) {	
		HashMap<String, Object> map = new HashMap<>();
		
		ArrayList<User> users = dalUser.get("cpf = '" + cpf +"'");
		
		if(users.size() == 1) {
			map.put(users.get(0).getId()+"", users.get(0));
		}
		
		return new ResponseEntity<>(map.values(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createUser(@RequestParam String cpf, @RequestParam String email) {
		if(!cpf.isEmpty() && !email.isEmpty()) {
			String apikey = HashGenerator.generate(32);
			
			User user = new User(cpf, email, apikey);
			
			if(dalUser.insert(user)) {
				return "{\"status\": true, \"id\": \"" + Conexao.getCon().getMaxPK("users", "id") + "\" ,\"apikey\": \""+apikey+"\"}";
			}
		}
		return "{\"status\": false}";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable String id, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			if(!id.isEmpty()) {
				if(dalUser.delete(Integer.parseInt(id))) {
					return "{\"status\": true}"; 
				}
			}
			
			return "{\"status\": false}";
		}
		
		return "{\"status\": false}";
	}
}
