package com.ibm.academia.restapi.ruleta.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.restapi.ruleta.models.entities.Apuesta;
import com.ibm.academia.restapi.ruleta.models.entities.Ruleta;

public interface RuletaService {
	
	public Integer crearRuleta();
	public Optional<Ruleta> buscarRuletaPorId(Integer id);
	public Boolean abrirRuleta(Ruleta ruleta);
	public Boolean CerrarRuleta(Ruleta ruleta);
	public Optional<Apuesta> ApostarPorNumero(Ruleta ruleta, Integer numero,Double monto);
	public Optional<Apuesta> ApostarPorColor(Ruleta ruleta,String color, Double monto);
	public List<Ruleta> buscarTodos();

}
