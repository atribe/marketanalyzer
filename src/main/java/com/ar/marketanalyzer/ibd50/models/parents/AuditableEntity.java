package com.ar.marketanalyzer.ibd50.models.parents;

import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class AuditableEntity extends PersistableEntityInt{
	
	private static final long serialVersionUID = -1206834257175517132L;

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
