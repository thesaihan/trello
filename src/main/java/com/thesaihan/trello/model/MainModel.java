package com.thesaihan.trello.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
		value = {"dateCreated", "lastUpdated"},
		allowGetters = true
		)
public class MainModel {
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created", updatable = false)
	@CreatedDate
	private Date dateCreated;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated")
	@LastModifiedDate
	private Date lastUpdated;

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
