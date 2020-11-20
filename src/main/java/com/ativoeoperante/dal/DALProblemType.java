package com.ativoeoperante.dal;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ativoeoperante.model.ProblemType;
import com.ativoeoperante.util.Conexao;

public class DALProblemType {
	public boolean insert(ProblemType p) {
		String SQL = "INSERT INTO problemtypes(name) VALUES('#1')";
		
		SQL = SQL.replaceAll("#1", p.getName());
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean update(ProblemType p) {
		String SQL = "UPDATE problemtypes SET name='#1' WHERE id='#2'";
		
		SQL = SQL.replaceAll("#1", p.getName());
		SQL = SQL.replaceAll("#2", p.getId()+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean delete(int id) {
		String SQL = "DELETE FROM problemtypes WHERE id='#1'";
		
		SQL = SQL.replaceAll("#1", id+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public ProblemType get(int id) {
		String SQL = "SELECT * FROM problemtypes WHERE id='#1'";
		ProblemType problemType = null;
		
		SQL = SQL.replaceAll("#1", id+"");
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				problemType = new ProblemType(
					rs.getInt("id"),
					rs.getString("name")
				);
			}
		}
		catch(Exception e) {
			
		}
		
		return problemType;
	}
	
	public ArrayList<ProblemType> get(String filter) {
		String SQL = "SELECT * FROM problemtypes";
		ArrayList<ProblemType> problemTypes = new ArrayList<ProblemType>();
		
		if(!filter.isEmpty())
			SQL += " WHERE " + filter;
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				problemTypes.add(
					new ProblemType(
					rs.getInt("id"),
					rs.getString("name")
				));
			}
		}
		catch(Exception e) {
			
		}
		
		return problemTypes;
	}
}
