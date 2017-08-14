package com.manaenko.rest;
//последний апдейт 14 августа
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Disk {

	public int id;
	public String title;
	public String genre;
	public String year;
	public int client;
	
	public Disk(String title, String genre, String year) {
		super();
		this.title = title;
		this.genre = genre;
		this.year = year;
	}
	
	public Disk(int id, String title, String genre, String year, int client) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.client = client;
	}
	
	public Disk(){
		
	}
	
	
}
