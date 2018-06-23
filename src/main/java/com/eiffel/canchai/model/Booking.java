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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idBooking")
    private Integer idBooking;
        
    @Column(name = "bookingDate")
    private String bookingDate;
        
    @Column(name = "status")
    private int status;
    
    @Column(name = "availableQuota")
    private Integer availableQuota;
    
    @Column(name = "gameDate")
    @Temporal(TemporalType.DATE)
    private Date gameDate;
    
    @Column(name = "gameTime")
    @Temporal(TemporalType.TIME)    
    private Date gameTime;
       

	@JoinColumn(name = "bookingType_idbookingType", referencedColumnName = "idbookingType")
    @ManyToOne(optional = false)
    private BookingType bookingType;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
    private List<Game> games;
    
    @JoinColumn(name = "Field_idField", referencedColumnName = "idField")
    @ManyToOne(optional = false)
    private Field field;
    
    @JoinColumn(name = "Player_idPlayer", referencedColumnName = "idPlayer")
    @ManyToOne(optional = false)
    private Player player;
    
    public Booking() {
    }

    public Booking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Booking(Integer idBooking, String bookingDate, int status) {
        this.idBooking = idBooking;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }
    
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
    
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Integer getAvailableQuota() {
		return availableQuota;
	}

	public void setAvailableQuota(Integer availableQuota) {
		this.availableQuota = availableQuota;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public Date getGameTime() {
		return gameTime;
	}

	public void setGameTime(Date gameTime) {
		this.gameTime = gameTime;
	}
	
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
