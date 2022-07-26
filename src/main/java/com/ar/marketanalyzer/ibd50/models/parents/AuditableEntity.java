package com.ar.marketanalyzer.ibd50.models.parents;

import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public class AuditableEntity extends PersistableEntityInt {

    private static final long serialVersionUID = -1206834257175517132L;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "modification_time", nullable = false)
    private LocalDateTime modificationTime;

    @PreUpdate
    public void preUpdate() {
        modificationTime = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        creationTime = now;
        modificationTime = now;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getModificationTime() {
        return modificationTime;
    }

    public LocalDate getLocalDateModificationTime() {
        return modificationTime.toLocalDate();
    }

    public void setModificationTime(LocalDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }
}
