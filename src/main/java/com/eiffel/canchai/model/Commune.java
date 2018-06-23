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
@Table(name = "commune")
public class Commune implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idCommune")
    private Integer idCommune;
        
    @Column(name = "description")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commune")
    @JsonIgnore
    private List<SportCenter> sportCenters;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commune")
    @JsonIgnore
    private List<Player> players;

    public Commune() {
    }

    public Commune(Integer idCommune) {
        this.idCommune = idCommune;
    }

    public Commune(Integer idCommune, String description) {
        this.idCommune = idCommune;
        this.description = description;
    }

    public Integer getIdCommune() {
        return idCommune;
    }

    public void setIdCommune(Integer idCommune) {
        this.idCommune = idCommune;
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

    public void setSportCenterSets(List<SportCenter> sportCenters) {
        this.sportCenters = sportCenters;
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players= players;
    }
}
