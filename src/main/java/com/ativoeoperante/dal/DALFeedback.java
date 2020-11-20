package com.ativoeoperante.dal;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ativoeoperante.model.Feedback;
import com.ativoeoperante.util.Conexao;

public class DALFeedback {
	public boolean insert(Feedback f) {
		String SQL = "INSERT INTO feedback(description, complaint_id) VALUES('#1', '#2')";
		
		SQL = SQL.replaceAll("#1", f.getDescription());
		SQL = SQL.replaceAll("#2", f.getComplaint().getId()+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public ArrayList<Feedback> get(int complaint_id) {
		String SQL = "SELECT * FROM feedback WHERE complaint_id='#1'";
		ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
		
		SQL = SQL.replaceAll("#1", complaint_id+"");
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				feedbacks.add(
					new Feedback(
						rs.getInt("id"),
						rs.getString("description"),
						new DALComplaint().get(rs.getInt("complaint_id"))
					)
				);
			}
		}
		catch(Exception e) {
			
		}
		
		return feedbacks;
	}
}
