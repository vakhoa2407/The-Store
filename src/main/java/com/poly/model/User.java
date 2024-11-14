package com.poly.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "[user]")
public class User implements Serializable{
	@Id
	private String id;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "admin")
	private boolean admin;
}
