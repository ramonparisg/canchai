package com.eiffel.canchai.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "player")
public class Player implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idPlayer")
    private Integer idPlayer;
        
    @Column(name = "birthDate")
    //@Temporal(TemporalType.DATE)
    private Date birthDate;
        
    @Column(name = "description")
    private String description;
    
    /*
    @JoinTable(name = "position_has_player", joinColumns = {
        @JoinColumn(name = "Player_idPlayer", referencedColumnName = "idPlayer")}, inverseJoinColumns = {
        @JoinColumn(name = "Position_idPosition", referencedColumnName = "idPosition")})
    @ManyToMany
    private List<Position> positions;
    */
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    @JsonIgnore
    private List<Booking> bookings;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    @JsonIgnore
    private List<Game> games;
    
    @JoinColumn(name = "Commune_idCommune", referencedColumnName = "idCommune")
    @ManyToOne(optional = false)
    private Commune commune;
    
    @Column(name = "image")
    private String image;
    
    @JoinColumn(name = "User_idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User user;

    public Player() {
    }

    public Player(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public Player(Integer idPlayer, Date birthDate) {
        this.idPlayer = idPlayer;
        this.birthDate = birthDate;
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
        
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
    
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
    
}
