package com.ar.marketanalyzer.ibd50.models.parents;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

@MappedSuperclass
public class PersitableEntity implements Serializable{
	
	@Version
	private static final long serialVersionUID = -1389117743389307160L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, unique=true)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_time", nullable = false)
	private Date creationTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modification_time", nullable = false)
	private Date modificationTime;
	
	@PreUpdate
    public void preUpdate() {
        modificationTime = new LocalDateTime().toDate();
    }
     
    @PrePersist
    public void prePersist() {
        Date now = new LocalDateTime().toDate();
        creationTime = now;
        modificationTime = now;
    }
    
    /*
	 * Getters and Setters
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}
	public LocalDate getLocalDateModificationTime() {
		return new LocalDate(modificationTime);
	}
	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
}
