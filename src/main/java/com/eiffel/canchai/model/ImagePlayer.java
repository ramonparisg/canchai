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
@Table(name = "imageplayer")
public class ImagePlayer implements Serializable{

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idImagePlayer")
    private Integer idImagePlayer;

    @Column(name = "url")
    private String url;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imagePlayer")
    @JsonIgnore
    private List<Player> players;

    public ImagePlayer() {
    }

    public ImagePlayer(Integer idImagePlayer) {
        this.idImagePlayer = idImagePlayer;
    }

    public ImagePlayer(Integer idImagePlayer, String url) {
        this.idImagePlayer = idImagePlayer;
        this.url = url;
    }

    public Integer getIdImagePlayer() {
        return idImagePlayer;
    }

    public void setIdImagePlayer(Integer idImagePlayer) {
        this.idImagePlayer = idImagePlayer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
