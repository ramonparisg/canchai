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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idBooking")
    private Integer idBooking;
        
    @Column(name = "bookingDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bookingDate;
        
    @Column(name = "status")
    @JsonIgnore
    private int status;
    
    @Column(name = "availableQuota")
    private Integer availableQuota;
    
    @Column(name = "gameDate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date gameDate;        
       

	@JoinColumn(name = "bookingType_idbookingType", referencedColumnName = "idbookingType")
    @ManyToOne(optional = false)
    private BookingType bookingType;
        
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "booking" )
    @JsonIgnore
    private Game game;
    
    @JoinColumn(name = "Field_idField", referencedColumnName = "idField")
    @ManyToOne(optional = false)
    private Field field;
    
    @JoinColumn(name = "Player_idPlayer", referencedColumnName = "idPlayer")
    @ManyToOne(optional = false)
    private Player player;
    
    @JoinColumn(name = "idBlockHour", referencedColumnName = "idBlockHour")
    @ManyToOne(optional = false)
    private BlockHour blockHour;
    
    public Booking() {
    }

    public Booking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

	public BlockHour getBlockHour() {
		return blockHour;
	}

	public void setBlockHour(BlockHour blockHour) {
		this.blockHour = blockHour;
	}
    
    
}
