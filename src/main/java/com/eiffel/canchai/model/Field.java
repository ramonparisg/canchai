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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "field")
public class Field implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idField")
    private Integer idField;
    
    @Column(name = "status")
    @JsonIgnore
    private short status;
    
    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy = "field")
    @JsonIgnore
    private List<Booking> bookings;
    
    @JoinColumn(name = "FieldType_idFieldType", referencedColumnName = "idFieldType")
    @ManyToOne(optional = false)
    private FieldType fieldType;
    
    @JoinColumn(name = "Sport_center_idSport_Center", referencedColumnName = "idSport_Center")
    @ManyToOne(optional = false)
    @JsonIgnore
    private SportCenter sportCenter;
    
    @JoinColumn(name = "Material_idMaterial", referencedColumnName = "idMaterial")
    @ManyToOne(optional = false)
    private Material material;
    
    public Field() {
    }

    public Field(Integer idField) {
        this.idField = idField;
    }

    public Field(Integer idField, short status) {
        this.idField = idField;
        this.status = status;
    }

    public Integer getIdField() {
        return idField;
    }

    public void setIdField(Integer idField) {
        this.idField = idField;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }       

    public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    @JsonIgnore
    public SportCenter getSportCenter() {
        return sportCenter;
    }

    @JsonProperty
    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
    
    
	
}
