package com.manaenko.rest;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.persistence.oxm.MediaType;



@Path("client")
public class ClientResource {

	public ClientService ds = new ClientService("jdbc:mysql://localhost:3306/disk_rent", "root", "");
	
	@Path("test")
	@GET
	public String myexception(){
				
		return "<h2>Resources Included</h2>";
	}
	
	@Path("post")	
	@POST
	//public String saveDisk(@QueryParam("some")String some){
	public String saveClient(
			@FormParam("id") int id,  
			@FormParam("fio") String fio,  
			@FormParam("balance") int balance,
			@FormParam("phone") String phone) throws SQLException {
		System.out.println("Client created - "+fio + " - " + balance + " - " + phone);
		
		Client client = new Client(id,fio, balance, phone);
		if (id == 0){
		ds.newClient(client);
		} else ds.updateClient(client);
		
		return "I got It-  "+ fio + " - " + balance + " - " + phone;
	}
	
	@Path("getclient/{id}")
	@GET
	public JsonObject getMailMessage(@PathParam("id") int id) throws SQLException{
		Client client = ds.getClient(id);
		
		return clientToJSON(client);
	}
	
	@Path("deleteclient/{id}")
	@GET
	public String deleteClient(@PathParam("id") int id) throws SQLException{
		ds.deleteClient(id);
		return "Клиент удалён - " + id;
	}
	
	@Path("allclients")
	@GET
	public List<Client> getAllClients() throws SQLException{
				
		return ds.listAllClients();
	}
	
	//Краткость сестра таланта. Лист мэйлов в массив JSON 
		@Path("alljson")
		@GET
		public JsonArray ClientToJson(@Context HttpHeaders http) throws SQLException{
			JsonArrayBuilder theBuilder = Json.createArrayBuilder();
			for(Client client : ds.listAllClients()) theBuilder.add(clientToJSON(client));
			return theBuilder.build();
		}

		//Преобразует объект MailMessage в JSON
	public JsonObject clientToJSON(Client ds2){
		
		JsonObjectBuilder theBuilder = Json.createObjectBuilder();
		
		theBuilder.add("id", ds2.id);	
		theBuilder.add("fio", ds2.fio);
		theBuilder.add("balance", ds2.balance);
		theBuilder.add("phone", ds2.phone);
		
		JsonObject jsonObject = theBuilder.build();
		//Json.createWriter(System.out).write(jsonObject);
		//response.
		return jsonObject;
		
	}
	
}
