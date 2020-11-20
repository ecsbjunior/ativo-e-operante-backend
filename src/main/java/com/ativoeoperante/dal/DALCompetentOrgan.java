package com.ativoeoperante.dal;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ativoeoperante.model.CompetentOrgan;
import com.ativoeoperante.util.Conexao;

public class DALCompetentOrgan {
	public boolean insert(CompetentOrgan c) {
		String SQL = "INSERT INTO competentorgans(name) VALUES('#1')";
		
		SQL = SQL.replaceAll("#1", c.getName());
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean update(CompetentOrgan c) {
		String SQL = "UPDATE competentorgans SET name='#1' WHERE id='#2'";
		
		SQL = SQL.replaceAll("#1", c.getName());
		SQL = SQL.replaceAll("#2", c.getId()+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean delete(int id) {
		String SQL = "DELETE FROM competentorgans WHERE id='#1'";
		
		SQL = SQL.replaceAll("#1", id+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public CompetentOrgan get(int id) {
		String SQL = "SELECT * FROM competentorgans WHERE id='#1'";
		CompetentOrgan competentOrgan = null;
		
		SQL = SQL.replaceAll("#1", id+"");
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				competentOrgan = new CompetentOrgan(
					rs.getInt("id"),
					rs.getString("name")
				);
			}
		}
		catch(Exception e) {
			
		}
		
		return competentOrgan;
	}
	
	public ArrayList<CompetentOrgan> get(String filter) {
		String SQL = "SELECT * FROM competentorgans";
		ArrayList<CompetentOrgan> competentOrgans = new ArrayList<CompetentOrgan>();
		
		if(!filter.isEmpty())
			SQL += " WHERE " + filter;
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				competentOrgans.add(
					new CompetentOrgan(
					rs.getInt("id"),
					rs.getString("name")
				));
			}
		}
		catch(Exception e) {
			
		}
		
		return competentOrgans;
	}
}
