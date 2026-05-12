package com.example.prol_educa.entities;

import com.example.prol_educa.utils.enuns.ERoles;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Roles")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ERoles type;

	public Roles() {
	}

	public Roles(@NotNull ERoles type) {
		super();
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERoles getType() {
		return type;
	}

	public void setType(ERoles type) {
		this.type = type;
	}
}
