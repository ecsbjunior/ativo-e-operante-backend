package com.ativoeoperante.dal;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ativoeoperante.model.Complaint;
import com.ativoeoperante.util.Conexao;

public class DALComplaint {
	public boolean insert(Complaint c) {
		String SQL = "INSERT INTO complaints(title, description, urgency, user_id, competentorgan_id, problemtype_id) VALUES('#1', '#2', '#3', '#4', '#5', '#6')";
		
		SQL = SQL.replaceAll("#1", c.getTitle());
		SQL = SQL.replaceAll("#2", c.getDescription());
		SQL = SQL.replaceAll("#3", c.getUrgency()+"");
		SQL = SQL.replaceAll("#4", c.getUser().getId()+"");
		SQL = SQL.replaceAll("#5", c.getCompetentOrgan().getId()+"");
		SQL = SQL.replaceAll("#6", c.getProblemType().getId()+"");
		
		boolean ans = Conexao.getCon().manipular(SQL);
		
		if(ans) {
			DALImage dalImage = new DALImage();
			
			c.setId(Conexao.getCon().getMaxPK("complaints", "id"));
			
			c.getImages().forEach((image) -> {
				dalImage.insert(image, c.getId());
			});
		}
		
		return ans;
	}
	
	public boolean update(Complaint c) {		
		String SQL = "UPDATE complaints SET title='#1', description='#2', urgency='#3' WHERE id='#4'";
		
		SQL = SQL.replaceAll("#1", c.getTitle());
		SQL = SQL.replaceAll("#2", c.getDescription());
		SQL = SQL.replaceAll("#3", c.getUrgency()+"");
		SQL = SQL.replaceAll("#4", c.getId()+"");
		

		boolean ans = Conexao.getCon().manipular(SQL);
		
		if(ans) {
			DALImage dalImage = new DALImage();
			c.getImages().forEach((image) -> {
				dalImage.insert(image, c.getId());
			});
		}
		
		return ans;
	}
	
	public boolean delete(int id) {
		String SQL = "DELETE FROM images WHERE complaint_id='#1'";
		
		SQL = SQL.replaceAll("#1", id+"");
		
		boolean ans = Conexao.getCon().manipular(SQL);
		
		if(ans) {
			SQL = "DELETE FROM complaints WHERE id='#1'";
			SQL = SQL.replaceAll("#1", id+"");
			
			ans = Conexao.getCon().manipular(SQL);
		}
		
		return ans;
	}
	
	public Complaint get(int id) {
		String SQL = "SELECT * FROM complaints WHERE id='#1'";
		Complaint complaint = null;
		
		SQL = SQL.replaceAll("#1", id+"");
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				complaint = new Complaint(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getString("description"),
					rs.getInt("urgency"),
					new DALUser().get(rs.getInt("user_id")),
					new DALCompetentOrgan().get(rs.getInt("competentorgan_id")),
					new DALProblemType().get(rs.getInt("problemtype_id")),
					new DALImage().get("complaint_id="+rs.getInt("id"))
				);
			}
		}
		catch(Exception e) {
			
		}
		
		return complaint;
	}
	
	public ArrayList<Complaint> get(String filter) {
		String SQL = "SELECT * FROM complaints";
		ArrayList<Complaint> complaints = new ArrayList<Complaint>();
		
		if(!filter.isEmpty())
			SQL += " WHERE " + filter;
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				complaints.add(new Complaint(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getString("description"),
					rs.getInt("urgency"),
					new DALUser().get(rs.getInt("user_id")),
					new DALCompetentOrgan().get(rs.getInt("competentorgan_id")),
					new DALProblemType().get(rs.getInt("problemtype_id")),
					new DALImage().get("complaint_id="+rs.getInt("id"))
				));
			}
		}
		catch(Exception e) {
			
		}
		
		return complaints;
	}
}
