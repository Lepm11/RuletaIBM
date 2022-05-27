package com.ibm.academia.restapi.ruleta.models.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApuestaDTO {
	
	private Integer id;
	
	private Double monto;
	
	private Integer numeroApostado;
	
	private String colorApostado;
	
	private String estadoApuesta;
	
	private Integer ruletaIds;

	@Override
	public String toString() {
		return "Apuesta: \nid=" + id + "\n, monto apostado=" + monto + ",\n numero apostado=" + numeroApostado + ",\n color apostado="
				+ colorApostado + ",\n estatus de apuesta=" + estadoApuesta + ",\n id de la ruleta=" + ruletaIds;
	}
	
	

}
