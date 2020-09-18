package com.XMLSecurity.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Authority")
public class Authority {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authority_id",unique = true, nullable = false)
	private int id;
	
	@Column(name="name",nullable = false)
	private String name;

	public Authority(int id, String name) {
		setId(id);
		setName(name);
	}
	public Authority() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
