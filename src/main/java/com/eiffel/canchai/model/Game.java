package com.eiffel.canchai.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "game")
public class Game implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idGame")
    private Integer idGame;           
    
    @JoinColumn(name = "Booking_idBooking", referencedColumnName = "idBooking")
    @OneToOne(optional = false)
    private Booking booking;
    
    @Column(name = "integrationDate")    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date integrationDate;
    
    @Column(name = "status")
    private Integer status;        
    
    @JoinColumn(name = "Player_idPlayer", referencedColumnName = "idPlayer")
    @ManyToOne(optional = false)
    private Player player;
    

    public Game() {
    }
    
    
    public Game(Booking booking, Date integrationDate, Integer status, Player player) {
		super();
		this.booking = booking;
		this.integrationDate = integrationDate;
		this.status = status;
		this.player = player;
	}


	public Date getIntegrationDate() {
		return integrationDate;
	}

	public void setIntegrationDate(Date integrationDate) {
		this.integrationDate = integrationDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

    public Game(Integer idGame) {
        this.idGame = idGame;
    }
   

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }


}
