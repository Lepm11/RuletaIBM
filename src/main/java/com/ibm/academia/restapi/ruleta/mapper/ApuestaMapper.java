package com.ibm.academia.restapi.ruleta.mapper;

import com.ibm.academia.restapi.ruleta.models.dto.ApuestaDTO;
import com.ibm.academia.restapi.ruleta.models.entities.Apuesta;


public class ApuestaMapper {
	
	public static ApuestaDTO mapapuestaDto(Apuesta apuesta) {
		ApuestaDTO apuestaDto = new ApuestaDTO();
		apuestaDto.setRuletaIds(apuesta.getRuletaIds());
		apuestaDto.setColorApostado(apuesta.getColorApostado());
		apuestaDto.setNumeroApostado(apuesta.getNumeroApostado());
		apuestaDto.setId(apuesta.getId());
		apuestaDto.setMonto(apuesta.getMonto());
		apuestaDto.setEstadoApuesta(apuesta.getEstadoApuesta());
		return apuestaDto;
		
	}

}
