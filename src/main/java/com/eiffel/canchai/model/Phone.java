package com.eiffel.canchai.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phone")
public class Phone implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idPhone")
    private Integer idPhone;
      
    @Column(name = "number")
    private long number;
    
    
    @JoinColumn(name = "Sport_center_idSport_Center", referencedColumnName = "idSport_Center")
    @ManyToOne(optional = false, fetch = FetchType.LAZY, targetEntity = SportCenter.class)
    @JsonIgnore
    private SportCenter sportCenter;

    public Phone() {
    }

    public Phone(Integer idPhone) {
        this.idPhone = idPhone;
    }

    public Phone(Integer idPhone, long number) {
        this.idPhone = idPhone;
        this.number = number;
    }

    public Integer getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(Integer idPhone) {
        this.idPhone = idPhone;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }
}
