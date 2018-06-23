package com.eiffel.canchai.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

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
        
    @Column(name = "description")
    private String description;
    
    @JoinTable(name = "sport_center_has_service", joinColumns = {
        @JoinColumn(name = "Service_idService", referencedColumnName = "idService")}, inverseJoinColumns = {
        @JoinColumn(name = "Sport_center_idSport_Center", referencedColumnName = "idSport_Center")})
    @ManyToMany
    private List<SportCenter> sportCenters;

    public Service() {
    }

    public Service(Integer idService) {
        this.idService = idService;
    }

    public Service(Integer idService, String name, String description) {
        this.idService = idService;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<SportCenter> getSportCenters() {
        return sportCenters;
    }

    public void setSportCenters(List<SportCenter> sportCenters) {
        this.sportCenters = sportCenters;
    }

}
