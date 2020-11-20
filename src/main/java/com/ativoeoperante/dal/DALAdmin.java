package com.ativoeoperante.dal;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ativoeoperante.model.Admin;
import com.ativoeoperante.util.Conexao;

public class DALAdmin {
	public boolean insert(Admin a) {
		String SQL = "INSERT INTO admins(username, password) VALUES('#1', '#2')";
		
		SQL = SQL.replaceAll("#1", a.getUsername());
		SQL = SQL.replaceAll("#2", a.getPassword());
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean update(Admin a) {
		String SQL = "UPDATE admins SET username='#1', password='#2' WHERE id='#3'";
		
		SQL = SQL.replaceAll("#1", a.getUsername());
		SQL = SQL.replaceAll("#2", a.getPassword());
		SQL = SQL.replaceAll("#3", a.getId()+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean delete(int id) {
		String SQL = "DELETE FROM admins WHERE id='#1'";
		
		SQL = SQL.replaceAll("#1", id+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public Admin get(int id) {
		String SQL = "SELECT * FROM admins WHERE id='#1'";
		Admin admin = null;
		
		SQL = SQL.replaceAll("#1", id+"");
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				admin = new Admin(
					rs.getInt("id"),
					rs.getString("username"),
					rs.getString("password")
				);
			}
		}
		catch(Exception e) {
			
		}
		
		return admin;
	}
	
	public ArrayList<Admin> get(String filter) {
		String SQL = "SELECT * FROM admins";
		ArrayList<Admin> admins = new ArrayList<Admin>();
		
		if(!filter.isEmpty())
			SQL += " WHERE " + filter;
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				admins.add(new Admin(
					rs.getInt("id"),
					rs.getString("username"),
					rs.getString("password")
				));
			}
		}
		catch(Exception e) {
			
		}
		
		return admins;
	}
}
