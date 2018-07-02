package com.eiffel.canchai.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "imagefield")
public class ImageField implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idImageField")
    private Integer idImageField;
        
    @Column(name = "url")
    private String url;
    
    @JoinColumn(name = "Sport_center_idSport_Center", referencedColumnName = "idSport_Center")
    @ManyToOne(optional = false)
    @JsonIgnore
    private SportCenter sportCenter;

    public ImageField() {
    }

    public ImageField(Integer idImageField) {
        this.idImageField = idImageField;
    }

    public ImageField(Integer idImageField, String url) {
        this.idImageField = idImageField;
        this.url = url;
    }

    
    
    public ImageField(String url, SportCenter sportCenter) {
		super();
		this.url = url;
		this.sportCenter = sportCenter;
	}

	public Integer getIdImageField() {
        return idImageField;
    }

    public void setIdImageField(Integer idImageField) {
        this.idImageField = idImageField;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public void setSportCenter(SportCenter sportCenter) {
        this.sportCenter = sportCenter;
    }

}
