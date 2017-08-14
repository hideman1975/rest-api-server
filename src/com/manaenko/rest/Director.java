package com.manaenko.rest;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Director {

	public int id;
	public String fio;
	public String birth_date;
	public String biography;
	
	public Director(){
		
	}

	public Director(int id, String fio, String birth_date, String biography) {
		super();
		this.id = id;
		this.fio = fio;
		this.birth_date = birth_date;
		this.biography = biography;
	}

	public Director(String fio, String birth_date, String biography) {
		super();
		this.fio = fio;
		this.birth_date = birth_date;
		this.biography = biography;
	}



}

