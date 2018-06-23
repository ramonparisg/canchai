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

@Entity
@Table(name = "field")
public class Field implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idField")
    private Integer idField;
    
    @Column(name = "status")
    private short status;
    
    @Column(name = "description")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "field")
    private List<Booking> bookings;
    
    @JoinColumn(name = "FieldType_idFieldType", referencedColumnName = "idFieldType")
    @ManyToOne(optional = false)
    private FieldType fieldType;
    
    @JoinColumn(name = "Sport_center_idSport_Center", referencedColumnName = "idSport_Center")
    @ManyToOne(optional = false)
    private SportCenter sportCenter;
    

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

    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }
	
}
