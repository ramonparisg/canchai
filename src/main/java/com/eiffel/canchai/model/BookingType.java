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

@Entity
@Table(name = "bookingtype")
public class BookingType implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idbookingType")
    private Integer idbookingType;
        
    @Column(name = "description")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingType")
    private List<Booking> bookings;

    public BookingType() {
    }

    public BookingType(Integer idbookingType) {
        this.idbookingType = idbookingType;
    }

    public BookingType(Integer idbookingType, String description) {
        this.idbookingType = idbookingType;
        this.description = description;
    }

    public Integer getIdbookingType() {
        return idbookingType;
    }

    public void setIdbookingType(Integer idbookingType) {
        this.idbookingType = idbookingType;
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
}
