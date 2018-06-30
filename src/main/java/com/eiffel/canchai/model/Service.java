package com.eiffel.canchai.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "service")
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idService")
    private Integer idService;
        
    @Column(name = "name")
    private String name;
    
    //, cascade = CascadeType.ALL
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "services") 
    @JsonIgnore
    private List<SportCenter> sportCenters;

    public Service() {
    }

    public Service(Integer idService) {
        this.idService = idService;
    }

    public Service(Integer idService, String name) {
        this.idService = idService;
        this.name = name;        
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public List<SportCenter> getSportCenters() {
        return sportCenters;
    }

    public void setSportCenters(List<SportCenter> sportCenters) {
        this.sportCenters = sportCenters;
    }
    
    public void addSportCenters(SportCenter sc) {    	
    	sportCenters.add(sc);
    }

}
