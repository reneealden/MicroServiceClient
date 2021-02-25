package com.example.demo.controller.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Client", schema="dbo")
public class ClientEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ClientId")
    private int clientId;		 
	@Column(name="Name") 
	private String name;	
	@Column(name="LastName") 
	private String lastName;
	@JsonFormat(pattern="dd/MM/yyyy", timezone="America/Lima")
	@Column(name="Birthday")
    private Date birthDate;
	@Column(name="FlagStatus")
    private String flagStatus; 
	@Column(name="CreationUser")
    private String creationUser; 
	@JsonFormat(pattern="dd/MM/yyyy hh:mm a", timezone="America/Lima")
	@Column(name="CreationDate")
    private Date creationDate;
	@Column(name="ModificationUser")
    private String modificationUser;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm a", timezone="America/Lima")
	@Column(name="ModificationDate")
    private Date modificationDate;
	
	@Formula("YEAR(GETDATE()) - YEAR(Birthday)")
    private String age;
	
	@PrePersist
    protected void prePersist() {
        if (this.creationDate == null) creationDate = new Date();
        if (this.flagStatus == null) flagStatus = "A";
        if (this.creationUser == null) creationUser = "Admin";
    }
}
