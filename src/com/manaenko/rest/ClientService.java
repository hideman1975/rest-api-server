package com.manaenko.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ClientService {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	
	public ClientService(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public List<Client> listAllClients() throws SQLException {
		List<Client> listClient = new ArrayList<>();
		
		String sql = "SELECT * FROM client";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			Client client = new Client();
			
			client.id = resultSet.getInt("id");
			client.fio = resultSet.getString("fio");
			client.balance = resultSet.getInt("balance");
			client.phone = resultSet.getString("phone");
			listClient.add(client);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listClient;
	}

	public Client getClient(int id) throws SQLException {
		Client client = new Client();
		String sql = "SELECT * FROM client WHERE `id` = "+id;
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		if (resultSet.next()) {
						
			client.id = resultSet.getInt("id");
			client.fio = resultSet.getString("fio");
			client.balance = resultSet.getInt("balance");
			client.phone = resultSet.getString("phone");
			
		}	else {
			client.id = 666;
			client.fio = "Emty disk";
			client.balance = 666;
			client.phone = "Emty disk";
		}
		resultSet.close();
		statement.close();
		
		disconnect();
		return client;
	}
	
	public void newClient(Client client) throws SQLException {
		
		String sql = "INSERT INTO `client` (`fio`, `balance`, `phone`) VALUES ('"+ client.fio +"', '"+ client.balance+"', '"+ client.phone+ "');";
		connect();
		Statement statement = jdbcConnection.createStatement();
		int resultSet = statement.executeUpdate(sql);
		statement.close();
		disconnect();
		
	}

public void updateClient(Client client) throws SQLException {
				
		String sql = "UPDATE `client` SET `fio` = '"+ client.fio +"',"
				+ " `balance` = '"+ client.balance+"', `phone` = '"+
				client.phone+"' WHERE `client`.`id` = "+ client.id+";";
		connect();
		Statement statement = jdbcConnection.createStatement();
		int resultSet = statement.executeUpdate(sql);
		statement.close();
		disconnect();
		
	}

public void deleteClient(int id) throws SQLException {
	String sql = "DELETE FROM `disk_rent`.`client` WHERE  `id`="+id+";";
	connect();
	Statement statement = jdbcConnection.createStatement();
	int resultSet = statement.executeUpdate(sql);
	statement.close();
	disconnect();
	
}
}//Конец описания класса 
