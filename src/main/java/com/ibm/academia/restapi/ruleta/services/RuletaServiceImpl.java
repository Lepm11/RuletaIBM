package com.ibm.academia.restapi.ruleta.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.ruleta.models.entities.Apuesta;
import com.ibm.academia.restapi.ruleta.models.entities.Ruleta;
import com.ibm.academia.restapi.ruleta.repositories.ApuestaRepository;
import com.ibm.academia.restapi.ruleta.repositories.RuletaRepository;

@Service
public class RuletaServiceImpl implements RuletaService{

	@Autowired
	private RuletaRepository ruletaRepository;
	
	@Autowired
	private ApuestaRepository apuestaRepository;

	
	@Override
	public Integer crearRuleta() {
		Ruleta nuevaRuleta = new Ruleta();
		nuevaRuleta=ruletaRepository.save(nuevaRuleta);
		return nuevaRuleta.getId();
	}
	
	@Override
	public Optional<Ruleta> buscarRuletaPorId(Integer id) {
		Optional<Ruleta> ruleta = ruletaRepository.findById(id);
		if (!ruleta.isPresent()) {
			//			throw new NotFoundException(String.format("El producto con ID %d no existe", id));
		}
		return ruleta;
	}

	@Override
	public Boolean abrirRuleta(Ruleta ruleta) {
		if (ruleta.getEstado()) {
			return true;
		}
		ruleta.setEstado(true);
		ruletaRepository.save(ruleta);
		return true;
	}

	@Override
	public Boolean CerrarRuleta(Ruleta ruleta) {
		if (ruleta.getEstado()) {
			ruleta.setEstado(false);
			ruletaRepository.save(ruleta);
			return true;
		}
		
		return true;
	}

	@Override
	public Optional<Apuesta> ApostarPorNumero(Ruleta ruleta, Integer numero,Double monto) {
		Apuesta apuesta = new Apuesta();
		apuesta.setRuleta(ruleta);
		apuesta.setMonto(monto);
		apuesta.setNumeroApostado(numero);
		apuesta.setRuletaIds(ruleta.getId());
		Integer resultadoObtenidoInteger = girarRuleta();
		System.out.println(numero);
		if(numero==(resultadoObtenidoInteger)) {
			apuesta.setEstadoApuesta("GANADA");
		}else {
			apuesta.setEstadoApuesta("PERDIDA");
		}
		Optional<Apuesta> resultadoApuesta = Optional.of(apuestaRepository.save(apuesta));
		
		return resultadoApuesta; 
	}

	@Override
	public Optional<Apuesta> ApostarPorColor(Ruleta ruleta, String color, Double monto) {
		if(color.equalsIgnoreCase("negro") ||color.equalsIgnoreCase("rojo")  ) {
			//soltar exception
		}
		Apuesta apuesta = new Apuesta();
		apuesta.setRuleta(ruleta);
		apuesta.setMonto(monto);
		apuesta.setColorApostado(color);
		apuesta.setRuletaIds(ruleta.getId());
		
		
		Integer resultadoObtenido = girarRuleta();
		if (resultadoObtenido % 2 == 0) {
			apuesta.setEstadoApuesta("GANADA");
		}else {
			apuesta.setEstadoApuesta("PERDIDA");
		}
		Optional<Apuesta> resultadoApuesta = Optional.of(apuestaRepository.save(apuesta));
		
		return resultadoApuesta; 
	}

	@Override
	public List<Ruleta> buscarTodos() {
		List<Ruleta> ruletas = (List<Ruleta>) ruletaRepository.findAll();
		if(ruletas.isEmpty()) {
			//throw
		}
		return ruletas;
	}
	
	private Integer girarRuleta() {
		Random random = new Random();
		int limiteMaximo = 37;
		return random.nextInt(limiteMaximo);
	}

}
