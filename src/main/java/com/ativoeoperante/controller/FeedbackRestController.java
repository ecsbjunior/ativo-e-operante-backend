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
import com.ativoeoperante.dal.DALComplaint;
import com.ativoeoperante.dal.DALFeedback;
import com.ativoeoperante.dal.DALUser;
import com.ativoeoperante.model.Feedback;

@RestController
@RequestMapping("/feedbacks")
@CrossOrigin(origins="*")
public class FeedbackRestController {
	private DALFeedback dalFeedback = new DALFeedback();
	private DALUser dalUser = new DALUser();
	private DALAdmin dalAdmin = new DALAdmin();
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getFeedbacks(@PathVariable String id, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			HashMap<String, Feedback> map = new HashMap<String, Feedback>();
			
			ArrayList<Feedback> feedbacks = dalFeedback.get(Integer.parseInt(id));
			
			for(Feedback feedback : feedbacks) {
				map.put(feedback.getId()+"", feedback);
			}
			
			return new ResponseEntity<>(map.values(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createCompetentOrgan(@RequestParam String description, @RequestParam String complaint_id, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			Feedback feedback = new Feedback(description, new DALComplaint().get(Integer.parseInt(complaint_id)));
			return "{\"status\": " + dalFeedback.insert(feedback) + "}";
		}
		
		return "{\"status\": false}"; 
	}
}
