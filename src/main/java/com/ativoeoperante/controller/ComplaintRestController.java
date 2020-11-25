package com.ativoeoperante.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.ativoeoperante.dal.DALCompetentOrgan;
import com.ativoeoperante.dal.DALComplaint;
import com.ativoeoperante.dal.DALProblemType;
import com.ativoeoperante.dal.DALUser;
import com.ativoeoperante.model.Complaint;
import com.ativoeoperante.model.Image;

@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins="*")
public class ComplaintRestController {
	private Path path = Paths.get("src/main/webapp/uploads");
	private DALComplaint dalComplaint = new DALComplaint();
	private DALUser dalUser = new DALUser();
	private DALAdmin dalAdmin = new DALAdmin();
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getComplaints(@RequestParam(required=false) String user_id, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			HashMap<String, Complaint> map = new HashMap<String, Complaint>();
			
			ArrayList<Complaint> complaints = dalComplaint.get(
					user_id == null || user_id.isEmpty() ? "" : "user_id = " + user_id
			);
			
			for(Complaint complaint : complaints) {
				map.put(complaint.getId()+"", complaint);
			}
			
			return new ResponseEntity<>(map.values(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getComplaint(@PathVariable String id, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			HashMap<String, Complaint> map = new HashMap<String, Complaint>();
			
			Complaint complaint = dalComplaint.get(Integer.parseInt(id));
			
			map.put(complaint.getId()+"", complaint);
			
			return new ResponseEntity<>(map.values(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createComplaint(@RequestParam String title, @RequestParam String description, @RequestParam String urgency, @RequestParam String user_id, @RequestParam String competentorgan_id, @RequestParam String problemtype_id, @RequestParam String apikey/*, @RequestParam(required=false) MultipartFile[] files*/) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			boolean ans = true;
	
			ArrayList<Image> images = new ArrayList<Image>();
			/*
			if(files != null) {
				for(MultipartFile file : files) {
					try {
						String fileName = HashGenerator.generate(16) + "-" + file.getOriginalFilename();
						Files.copy(file.getInputStream(), this.path.resolve(fileName));
						images.add(new Image(fileName));
					}
					catch(IOException e) {
						System.out.println("[ERRO]: " + e.getMessage());
						ans = false;
					}
				}
			}
			*/
			
			if(ans) {
				Complaint complaint = new Complaint(
					title,
					description,
					Integer.parseInt(urgency),
					new DALUser().get(Integer.parseInt(user_id)),
					new DALCompetentOrgan().get(Integer.parseInt(competentorgan_id)),
					new DALProblemType().get(Integer.parseInt(problemtype_id)),
					images
				);
					
				ans = dalComplaint.insert(complaint);
			}
			
			return "{\"status\": " + ans + "}";
		}
		
		return "{\"status\": false}";
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public String deleteComplaint(@RequestParam String id, @RequestParam String apikey) {
		if(dalUser.validade(apikey) || dalAdmin.validade(apikey)) {
			return "{\"status\": " + dalComplaint.delete(Integer.parseInt(id)) + "}";
		}
		return "{\"status\": false}";
	}
}
