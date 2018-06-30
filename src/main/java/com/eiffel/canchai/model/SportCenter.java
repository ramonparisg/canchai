package com.eiffel.canchai.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sport_center")
public class SportCenter implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Column(name = "idSport_Center")
    private Integer idSportCenter;
        
    @Column(name = "name")
    private String name;
           
    @Column(name = "address")
    private String address;
        
    @Column(name = "description")
    private String description;
    
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation    
    @Column(name = "email")
    private String email;
    
    @JoinTable(name = "sport_center_has_user", joinColumns = {
        @JoinColumn(name = "Sport_center_idSport_Center", referencedColumnName = "idSport_Center")}, inverseJoinColumns = {
        @JoinColumn(name = "User_idUser", referencedColumnName = "idUser")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;
    
    
    @JoinTable(name = "sport_center_has_service", joinColumns = {
    @JoinColumn(name = "Sport_center_idSport_Center", referencedColumnName = "idSport_Center")}, inverseJoinColumns = {    
    @JoinColumn(name = "Service_idService", referencedColumnName = "idService")})
    @ManyToMany(cascade = CascadeType.ALL)        
    private List<Service> services;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sportCenter")  
    @JsonIgnore
    private List<ImageField> imageFields;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sportCenter")    
    private List<Field> fields;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sportCenter", fetch = FetchType.LAZY)    
    private List<Phone> phones;
    
    @JoinColumn(name = "Commune_idCommune", referencedColumnName = "idCommune")
    @ManyToOne(optional = false)    
    private Commune commune;
    

    public SportCenter() {
    }

    public SportCenter(Integer idSportCenter) {
        this.idSportCenter = idSportCenter;
    }

    public SportCenter(Integer idSportCenter, String name, String address, String email) {
        this.idSportCenter = idSportCenter;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Integer getIdSportCenter() {
        return idSportCenter;
    }

    public void setIdSportCenter(Integer idSportCenter) {
        this.idSportCenter = idSportCenter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
    
    public List<ImageField> getImageFields() {
        return imageFields;
    }

    public void setImageFields(List<ImageField> imageFields) {
        this.imageFields = imageFields;
    }
    
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
    
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }
}
