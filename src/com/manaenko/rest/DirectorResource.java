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



@Path("director")
public class DirectorResource {

	public DirectorService ds = new DirectorService("jdbc:mysql://localhost:3306/disk_rent", "root", "");
	
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
			@FormParam("fio") String fio,  
			@FormParam("birth_date") String birth_date,
			@FormParam("biography") String biography) throws SQLException {
		System.out.println("Director created - "+fio + " - " + birth_date + " - " + biography);
		
		Director director = new Director(id,fio, birth_date, biography);
		if (id == 0){
		ds.newDirector(director);
		} else ds.updateDirector(director);
		
		return "I got It-  "+ fio + " - " + birth_date + " - " + biography;
	}
	
	@Path("getdirector/{id}")
	@GET
	public JsonObject getMailMessage(@PathParam("id") int id) throws SQLException{
		Director director = ds.getDirector(id);
		
		return directorToJSON(director);
	}
	
	@Path("deletedirector/{id}")
	@GET
	public String deleteDirector(@PathParam("id") int id) throws SQLException{
		ds.deleteDirector(id);
		return "Диск удалён - " + id;
	}
	
	@Path("alldirectors")
	@GET
	public List<Director> getAllDirectors() throws SQLException{
				
		return ds.listAllDirectors();
	}
	
	//Краткость сестра таланта. Лист мэйлов в массив JSON 
		@Path("alljson")
		@GET
		public JsonArray DirectorToJson(@Context HttpHeaders http) throws SQLException{
			JsonArrayBuilder theBuilder = Json.createArrayBuilder();
			for(Director director : ds.listAllDirectors()) theBuilder.add(directorToJSON(director));
			return theBuilder.build();
		}

		//Преобразует объект MailMessage в JSON
	public JsonObject directorToJSON(Director ds2){
		
		JsonObjectBuilder theBuilder = Json.createObjectBuilder();
		
		theBuilder.add("id", ds2.id);	
		theBuilder.add("fio", ds2.fio);
		theBuilder.add("birth_date", ds2.birth_date);
		theBuilder.add("biography", ds2.biography);
		
		JsonObject jsonObject = theBuilder.build();
		//Json.createWriter(System.out).write(jsonObject);
		//response.
		return jsonObject;
		
	}
	
}
