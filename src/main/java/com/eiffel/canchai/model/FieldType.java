package com.eiffel.canchai.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fieldtype")
public class FieldType implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idFieldType")
    private Integer idFieldType;
        
    @Column(name = "capacity")
    private int capacity;
        
    @Column(name = "name")
    private String name;        
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fieldType")
    @JsonIgnore
    private List<Field> fields;

    public FieldType() {
    }

    public FieldType(Integer idFieldType) {
        this.idFieldType = idFieldType;
    }

    public FieldType(Integer idFieldType, int capacity, String name) {
        this.idFieldType = idFieldType;
        this.capacity = capacity;
        this.name = name;
    }

    public Integer getIdFieldType() {
        return idFieldType;
    }

    public void setIdFieldType(Integer idFieldType) {
        this.idFieldType = idFieldType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
