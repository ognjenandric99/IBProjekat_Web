package com.XMLSecurity.Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id",unique = true, nullable = false)
	private int id;
	
	@Column(name="email",nullable = false)
	private String email;
	
	@Column(name="activeSession", nullable = true)
	private String activeSession;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="certificate",nullable = true)
	private String certificate;
	
	@Column(name="active",nullable = false)
	private Boolean active;
	
	@ManyToOne
	@JoinColumn(name="authority_id", referencedColumnName="authority_id", nullable=false)
	private Authority authority;
	
	public User() {
		
	}
	public User(int id, String email, String password, String certificate, Boolean active, Authority authority) {
		setId(id);
		setEmail(email);
		setPassword(password);
		setCertificate(certificate);
		setActive(active);
		setAuthority(authority);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	public String getActiveSession() {
		return activeSession;
	}
	public void setActiveSession(String activeSession) {
		this.activeSession = activeSession;
	}
	
	
}
