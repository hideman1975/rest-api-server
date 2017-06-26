package com.manaenko.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DiskService {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	
	public DiskService(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public List<Disk> listAllDisks() throws SQLException {
		List<Disk> listDisk = new ArrayList<>();
		
		String sql = "SELECT * FROM disk";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			Disk disk = new Disk();
			
			disk.id = resultSet.getInt("id");
			disk.title = resultSet.getString("title");
			disk.genre = resultSet.getString("genre");
			disk.year = resultSet.getString("year");
			listDisk.add(disk);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listDisk;
	}

	public Disk getDisk(int id) throws SQLException {
		Disk disk = new Disk();
		String sql = "SELECT * FROM disk WHERE `id` = "+id;
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		if (resultSet.next()) {
						
			disk.id = resultSet.getInt("id");
			disk.title = resultSet.getString("title");
			disk.genre = resultSet.getString("genre");
			disk.year = resultSet.getString("year");
			
		}	else {
			disk.id = 666;
			disk.title = "Emty disk";
			disk.genre = "Emty disk";
			disk.year = "Emty disk";
		}
		resultSet.close();
		statement.close();
		
		disconnect();
		return disk;
	}
	
	public void newDisk(Disk disk) throws SQLException {
		
		String sql = "INSERT INTO `disk` (`title`, `genre`, `year`) VALUES ('"+ disk.title +"', '"+ disk.genre+"', '"+ disk.year+ "');";
		connect();
		Statement statement = jdbcConnection.createStatement();
		int resultSet = statement.executeUpdate(sql);
		statement.close();
		disconnect();
		
	}

public void updateDisk(Disk disk) throws SQLException {
				
		String sql = "UPDATE `disk` SET `title` = '"+ disk.title +"',"
				+ " `genre` = '"+ disk.genre+"', `year` = '"+
				disk.year+"' WHERE `disk`.`id` = "+ disk.id+";";
		connect();
		Statement statement = jdbcConnection.createStatement();
		int resultSet = statement.executeUpdate(sql);
		statement.close();
		disconnect();
		
	}

public void deleteDisk(int id) throws SQLException {
	String sql = "DELETE FROM `mysql`.`disk` WHERE  `id`="+id+";";
	connect();
	Statement statement = jdbcConnection.createStatement();
	int resultSet = statement.executeUpdate(sql);
	statement.close();
	disconnect();
	
}
}//Конец описания класса 
