package com.ar.marketanalyzer.core.securities.models.parents;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class PersistableEntityInt implements Serializable{
	
	private static final long serialVersionUID = -1389117743389307160L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, unique=true)
	protected Integer id;
	
    /*
	 * Getters and Setters
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setId(Long id) {
		this.id = id.intValue();
	}
	
}
