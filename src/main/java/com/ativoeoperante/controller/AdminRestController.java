package com.ativoeoperante.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ativoeoperante.dal.DALAdmin;
import com.ativoeoperante.model.Admin;
import com.ativoeoperante.util.HashGenerator;

@RestController
@RequestMapping("/admins")
@CrossOrigin(origins="*")
public class AdminRestController {
	@RequestMapping(method=RequestMethod.GET)
	public String validateAdmin(@RequestParam String username, @RequestParam String password) {
		String search = "username='#1' AND password='#2'";
		
		search = search.replaceAll("#1", username);
		search = search.replaceAll("#2", password);
		
		ArrayList<Admin> admins = new DALAdmin().get(search);
		
		if(admins.size() == 1)
			return "{" +
				       "\"token\":" + "\"" + admins.get(0).getApiKey() + "\"," +
				       "\"username\":" + "\"" + admins.get(0).getUsername() + "\"" +
				   "}";
		
		return "{" +
			       "\"token\":" + "\"\"" + "," +
			       "\"username\":" + "\"\"" +
	       	   "}";
	}
}
