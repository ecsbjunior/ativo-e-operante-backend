package com.ativoeoperante.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ativoeoperante.model.User;
import com.ativoeoperante.util.Conexao;

public class DALUser {
	public boolean insert(User u) {
		String SQL = "INSERT INTO users(cpf, email, apikey) VALUES('#1', '#2', '#3')";
		
		SQL = SQL.replaceAll("#1", u.getCPF());
		SQL = SQL.replaceAll("#2", u.getEmail());
		SQL = SQL.replaceAll("#3", u.getAPIKEY());
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean update(User u) {
		String SQL = "UPDATE users SET cpf='#1', email='#2', apikey='#3' WHERE id='#4'";
		
		SQL = SQL.replaceAll("#1", u.getCPF());
		SQL = SQL.replaceAll("#2", u.getEmail());
		SQL = SQL.replaceAll("#3", u.getAPIKEY());
		SQL = SQL.replaceAll("#4", u.getId()+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean delete(int id) {
		String SQL = "DELETE FROM users WHERE id='#1'";
		
		SQL = SQL.replaceAll("#1", id+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean validade(String apikey) {
		String SQL = "SELECT * FROM users WHERE apikey='#1'";
		
		SQL = SQL.replaceAll("#1", apikey);
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			return rs.next();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public User get(int id) {
		String SQL = "SELECT * FROM users WHERE id='#1'";
		User user = null;
		
		SQL = SQL.replaceAll("#1", id+"");
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				user = new User(
					rs.getInt("id"),
					rs.getString("cpf"),
					rs.getString("email"),
					rs.getString("apikey")
				);
			}
		}
		catch(Exception e) {
			
		}
		
		return user;
	}
	
	public ArrayList<User> get(String filter) {
		String SQL = "SELECT * FROM users";
		ArrayList<User> users = new ArrayList<User>();
		
		if(!filter.isEmpty())
			SQL += " WHERE " + filter;
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				users.add(new User(
					rs.getInt("id"),
					rs.getString("cpf"),
					rs.getString("email"),
					rs.getString("apikey")
				));
			}
		}
		catch(Exception e) {
			
		}
		
		return users;
	}
}
