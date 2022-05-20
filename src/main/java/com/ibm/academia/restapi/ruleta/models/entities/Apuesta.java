package com.ibm.academia.restapi.ruleta.models.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apuesta implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "monto_apuesta")
	
	private Double monto;
	
	@Column(name = "numero_apostado")
	@Min(value = 0, message = "El número no puede ser menor a 0")
	@Max(value = 36, message = "El número no puede ser mayor a 36")
	private Integer numeroApostado;
	
	@Column(name = "color_apostado")
	private String colorApostado;
	
	private String estadoApuesta;
	
	private Integer ruletaIds;
	
	//@ManyToOne(cascade = CascadeType.MERGE)
	@ManyToOne(optional = true , fetch = FetchType.LAZY , cascade = { CascadeType.PERSIST , CascadeType.MERGE })
	@JsonIgnoreProperties({"hibernateLazyInitializer", "apuestas"})
	private Ruleta ruleta;
	
	private static final long serialVersionUID = 4156415265831922788L;

}
