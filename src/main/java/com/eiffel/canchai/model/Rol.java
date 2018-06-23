package com.eiffel.canchai.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rol")
public class Rol implements Serializable{
	 private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)	    
	    @Column(name = "idRol")
	    private Integer idRol;
	    	    
	    @Column(name = "description")
	    private String description;
	    
	    
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
	    @JsonIgnore
	    private List<User> users;

	    public Rol() {
	    }

	    public Rol(Integer idRol) {
	        this.idRol = idRol;
	    }

	    public Rol(Integer idRol, String description) {
	        this.idRol = idRol;
	        this.description = description;
	    }

	    public Integer getIdRol() {
	        return idRol;
	    }

	    public void setIdRol(Integer idRol) {
	        this.idRol = idRol;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    	    
	    public List<User> getUsers() {
	        return users;
	    }

	    public void setUsers(List<User> users) {
	        this.users = users;
	    }
}
