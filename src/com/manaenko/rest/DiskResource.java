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



@Path("disks")
public class DiskResource {

	public DiskService ds = new DiskService("jdbc:mysql://localhost:3306/disk_rent", "root", "");
	
	@Path("test")
	@GET
	public String myexception(){
				
		return "<h2>Resources Included</h2>";
	}
	
	@Path("post")	
	@POST
	//public String saveDisk(@QueryParam("some")String some){
	public String saveDisk(
			@FormParam("id") int id,  
			@FormParam("title") String title,  
			@FormParam("year") String year,
			@FormParam("genre") String genre,
			@FormParam("client") int client)
	throws SQLException {
		System.out.println("Фильм создан - "+title + " - " + genre + " - " + year);
		
		Disk disk = new Disk(id,title, genre, year, client);
		if (id == 0){
		ds.newDisk(disk);
		} else ds.updateDisk(disk);
		
		return "I got It-  "+ title + " - " + genre + " - " + year;
	}
	
	@Path("getdisk/{id}")
	@GET
	public JsonObject getMailMessage(@PathParam("id") int id) throws SQLException{
		Disk disk = ds.getDisk(id);
		
		return diskToJSON(disk);
	}
	
	@Path("deletedisk/{id}")
	@GET
	public String deleteDisk(@PathParam("id") int id) throws SQLException{
		ds.deleteDisk(id);
		return "Диск удалён - " + id;
	}
	
	@Path("alldisks")
	@GET
	public List<Disk> getAllDisks() throws SQLException{
				
		return ds.listAllDisks();
	}
	
	//Краткость сестра таланта. Лист мэйлов в массив JSON 
		@Path("alljson")
		@GET
		public JsonArray DisksToJson(@Context HttpHeaders http) throws SQLException{
			JsonArrayBuilder theBuilder = Json.createArrayBuilder();
			for(Disk disk : ds.listAllDisks()) theBuilder.add(diskToJSON(disk));
			return theBuilder.build();
		}
		
		@Path("director_id/{directorId}")
		@GET
		public JsonArray SelectToJson(@PathParam("directorId") int directorId) throws SQLException{
			JsonArrayBuilder theBuilder = Json.createArrayBuilder();
			for(Disk disk : ds.listByDirector(directorId)) theBuilder.add(diskToJSON(disk));
			return theBuilder.build();
		}
		
		@Path("client_id/{clientId}")
		@GET
		public JsonArray SelectToJsonByClient(@PathParam("clientId") int clientId) throws SQLException{
			JsonArrayBuilder theBuilder = Json.createArrayBuilder();
			for(Disk disk : ds.listByClient(clientId)) theBuilder.add(diskToJSON(disk));
			return theBuilder.build();
		}

		//Преобразует объект MailMessage в JSON
	public JsonObject diskToJSON(Disk ds2){
		
		JsonObjectBuilder theBuilder = Json.createObjectBuilder();
			theBuilder.add("id", ds2.id);	
			theBuilder.add("title", ds2.title);
			theBuilder.add("genre", ds2.genre);
			theBuilder.add("year", ds2.year);
			theBuilder.add("client", ds2.client);
		JsonObject jsonObject = theBuilder.build();
		//Json.createWriter(System.out).write(jsonObject);
		//response.
		return jsonObject;
		
	}
	
}
