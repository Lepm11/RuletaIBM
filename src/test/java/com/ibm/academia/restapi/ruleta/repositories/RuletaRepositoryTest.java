package com.ibm.academia.restapi.ruleta.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.ruleta.datos.DatosDummy;
import com.ibm.academia.restapi.ruleta.models.entities.Apuesta;
import com.ibm.academia.restapi.ruleta.models.entities.Ruleta;

@DataJpaTest
public class RuletaRepositoryTest {
	/*
	@Autowired
	private RuletaRepository ruletaRepository;
	
	@Autowired
	private ApuestaRepository apuestaRepository;
	
	@BeforeEach
	void setUp() {
		
	}
	
	@AfterEach
	void tearDown() {
		ruletaRepository.deleteAll();
	}

	@Test
	@DisplayName("Test: Buscar todas las ruletas")
	void EncontrarTodos() {
	
		ruletaRepository.save(DatosDummy.ruleta01());

		List<Ruleta> ruletas = (List<Ruleta>)ruletaRepository.findAll();
		
		assertThat(ruletas.size() == 1).isTrue();
	}
	
	@Test
	@DisplayName("Test: Crear una nueva ruleta")
	void crearRuleta() {
		Ruleta nuevaRuleta = ruletaRepository.save(DatosDummy.ruleta02());
		assertNotNull(nuevaRuleta);
	}
	@Test
	@DisplayName("Test: Buscar una ruleta por Id")
	void buscarRuletaPorId() {
		Ruleta expectedRuleta = ruletaRepository.save(DatosDummy.ruleta03());
		assertThat(expectedRuleta.getId() == 1).isTrue();
	}
	
	@Test
	@DisplayName("Test: Apostar por nombre")
	void apostarPorNombre() {
		Ruleta ruleta04 = new Ruleta();
		Apuesta apuesta = new Apuesta();
		Double montoDouble = 1000d;
		apuesta.setRuleta(ruleta04);
		apuesta.setMonto(montoDouble);
		apuesta.setNumeroApostado(36);
		Integer resultadoObtenidoInteger = DatosDummy.girarRuleta();
		System.out.println(36);
		if(36==(resultadoObtenidoInteger)) {
			apuesta.setEstadoApuesta("GANADA");
		}else {
			apuesta.setEstadoApuesta("PERDIDA");
		}
		Optional<Apuesta> resultadoApuesta = Optional.of(apuestaRepository.save(apuesta));
		assertThat(resultadoApuesta.get().getEstadoApuesta().equalsIgnoreCase("ganada")||resultadoApuesta.get().getEstadoApuesta().equalsIgnoreCase("perdida")).isTrue();
		assertNotNull(resultadoApuesta);
	}
*/
	
}
