package com.manaenko.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Client
{

	public int id;
	public String fio;
	public int balance;
	public String phone;
	
	
	public Client(String fio, int balance, String phone) {
		super();
		this.fio = fio;
		this.balance = balance;
		this.phone = phone;
	}
	
	public Client(int id, String fio, int balance, String phone) {
		super();
		this.id = id;
		this.fio = fio;
		this.balance = balance;
		this.phone = phone;
	}
	
	public Client(){
		
	}
	
	
}
