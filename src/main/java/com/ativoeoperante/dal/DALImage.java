package com.ativoeoperante.dal;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ativoeoperante.model.Image;
import com.ativoeoperante.util.Conexao;

public class DALImage {
	public boolean insert(Image i, int complaint_id) {
		String SQL = "INSERT INTO images(path, complaint_id) VALUES('#1', '#2')";
		
		SQL = SQL.replaceAll("#1", i.getPath());
		SQL = SQL.replaceAll("#2", complaint_id+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean update(Image i) {
		String SQL = "UPDATE images SET path='#1' WHERE id='#2'";
		
		SQL = SQL.replaceAll("#1", i.getPath());
		SQL = SQL.replaceAll("#2", i.getId()+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public boolean delete(int id) {
		String SQL = "DELETE FROM images WHERE id='#1'";
		
		SQL = SQL.replaceAll("#1", id+"");
		
		return Conexao.getCon().manipular(SQL);
	}
	
	public Image get(int id) {
		String SQL = "SELECT * FROM images WHERE id='#1'";
		Image image = null;
		
		SQL = SQL.replaceAll("#1", id+"");
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				image = new Image(
					rs.getInt("id"),
					rs.getString("path")
				);
			}
		}
		catch(Exception e) {
			
		}
		
		return image;
	}
	
	public ArrayList<Image> get(String filter) {
		String SQL = "SELECT * FROM images";
		ArrayList<Image> images = new ArrayList<Image>();
		
		if(!filter.isEmpty())
			SQL += " WHERE " + filter;
		
		ResultSet rs = Conexao.getCon().consultar(SQL);
		
		try {
			while(rs.next()) {
				images.add(new Image(
					rs.getInt("id"),
					rs.getString("path")
				));
			}
		}
		catch(Exception e) {
			
		}
		
		return images;
	}
}
