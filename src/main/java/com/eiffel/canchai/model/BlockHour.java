package com.eiffel.canchai.model;

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
@Table(name = "blockhour")
public class BlockHour {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idBlockHour")
    private Integer idBlockHour;
    
    @Column(name = "hour")
    private String hour;
    
    @OneToMany(mappedBy = "blockHour")
    @JsonIgnore
    private List<Booking> bookings;

    
    
	public BlockHour() {
		super();
	}
	
	

	public Integer getIdBlockHour() {
		return idBlockHour;
	}

	public void setIdBlockHour(Integer idBlockHour) {
		this.idBlockHour = idBlockHour;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
    
    
}


