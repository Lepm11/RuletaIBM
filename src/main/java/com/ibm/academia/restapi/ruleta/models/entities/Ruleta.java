package com.ibm.academia.restapi.ruleta.models.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ruleta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "estado",nullable = false)
	private Boolean estado;
	

	
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	@OneToMany(mappedBy = "ruleta",fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","ruleta"})
	private List<Apuesta> apuestas;
	
	@PrePersist
	private void beforePersist() {
		fechaCreacion = new Date();
		estado = false;
		
	}
	
	@PreUpdate
	private void beforeUpdate() {
		fechaModificacion = new Date();
	}
	
	
	private static final long serialVersionUID = -5444460553750128554L;

}
