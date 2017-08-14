package com.manaenko.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DirectorService {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	
	public DirectorService(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public List<Director> listAllDirectors() throws SQLException {
		List<Director> listDirector = new ArrayList<>();
		
		String sql = "SELECT * FROM films_director";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			Director director = new Director();
			
			director.id = resultSet.getInt("id");
			director.fio = resultSet.getString("fio");
			director.birth_date = resultSet.getString("birth_date");
			director.biography = resultSet.getString("biography");
			listDirector.add(director);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listDirector;
	}

	public Director getDirector(int id) throws SQLException {
		Director director = new Director();
		String sql = "SELECT * FROM films_director WHERE `id` = "+id;
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		if (resultSet.next()) {
						
			director.id = resultSet.getInt("id");
			director.fio = resultSet.getString("fio");
			director.birth_date = resultSet.getString("birth_date");
			director.biography = resultSet.getString("biography");
			
		}	else {
			director.id = 666;
			director.fio = "Emty disk";
			director.birth_date = "Emty disk";
			director.biography = "Emty disk";
		}
		resultSet.close();
		statement.close();
		
		disconnect();
		return director;
	}
	
	public void newDirector(Director director) throws SQLException {
		
		String sql = "INSERT INTO `films_director` (`fio`, `birth_date`, `biography`) VALUES ('"+ director.fio +"', '"+ director.birth_date+"', '"+ director.biography+ "');";
		connect();
		Statement statement = jdbcConnection.createStatement();
		int resultSet = statement.executeUpdate(sql);
		statement.close();
		disconnect();
		
	}

public void updateDirector(Director director) throws SQLException {
				
		String sql = "UPDATE `films_director` SET `fio` = '"+ director.fio +"',"
				+ " `birth_date` = '"+ director.birth_date+"', `biography` = '"+
				director.biography+"' WHERE `films_director`.`id` = "+ director.id+";";
		connect();
		Statement statement = jdbcConnection.createStatement();
		int resultSet = statement.executeUpdate(sql);
		statement.close();
		disconnect();
		
	}

public void deleteDirector(int id) throws SQLException {
	String sql = "DELETE FROM `disk_rent`.`films_director` WHERE  `id`="+id+";";
	connect();
	Statement statement = jdbcConnection.createStatement();
	int resultSet = statement.executeUpdate(sql);
	statement.close();
	disconnect();
	
}
}//Конец описания класса 
