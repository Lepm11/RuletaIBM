package com.ibm.academia.restapi.ruleta.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.ruleta.exception.BadRequestException;
import com.ibm.academia.restapi.ruleta.exception.NotFoundException;
import com.ibm.academia.restapi.ruleta.exception.RuletaNotOpenedException;
import com.ibm.academia.restapi.ruleta.models.entities.Apuesta;
import com.ibm.academia.restapi.ruleta.models.entities.Ruleta;
import com.ibm.academia.restapi.ruleta.services.RuletaService;

@RestController
@RequestMapping("/ruletas")
public class RuletaController {
	
	Logger logger = LoggerFactory.getLogger(RuletaController.class);
	
	@Autowired
	private RuletaService ruletaService;

	/**
	 *  Endpoint para crear una nueva ruleta, por defecto se crea cerrada
	 * @return regresa el id de la ruleta creada.
	 * @author LEPM
	 */
	@PostMapping("/crearRuleta")
	 public ResponseEntity<?> crearRuleta(){
			return ResponseEntity.status(HttpStatus.CREATED).body(ruletaService.crearRuleta());

	 }
	/**
	 *  Endpoint para abrir una ruleta por Id
	 * @param ruletaId, el Id de la ruleta
	 * @return Retorna la ruleta
	 * @throws NotFoundException si no existe la ruleta con ese ID o no se puede abrir.
	 * @author LEPM
	 */
	@PutMapping("/abrirRuleta/{ruletaId}")
	public ResponseEntity<?> abrirRuleta(@PathVariable Integer ruletaId){
		
		Optional<Ruleta> ruleta = ruletaService.buscarRuletaPorId(ruletaId);
		if (!ruleta.isPresent()) {
			throw new NotFoundException(String.format("No existe ruleta con el ID: %d", ruletaId));
		}
		if(ruleta.get().getEstado()) {
			throw new BadRequestException(String.format("la ruleta con el ID: %d ya está abierta", ruletaId));
		}
		Boolean resultado = ruletaService.abrirRuleta(ruleta.get());
		if (!resultado) {
			throw new NotFoundException(String.format("No se pudo abrir la ruleta con el ID %d",ruletaId));
		}
		return new ResponseEntity<Ruleta>(ruleta.get(), HttpStatus.OK);

	}
	
	/**
	 *  Endpoint para cerrar una ruleta por Id
	 * @param ruletaId, ID de la ruleta por cerrar
	 * @return Retorna la ruleta cerrada
	 * @throws NotFoundException en caso de no encontrar la ruleta
	 * @author LEPM
	 */
	@PutMapping("/cerrarRuleta/{ruletaId}")
	public ResponseEntity<?> cerrarRuleta(@PathVariable Integer ruletaId){
		
		Optional<Ruleta> ruleta = ruletaService.buscarRuletaPorId(ruletaId);
		if (!ruleta.isPresent()) {
			throw new NotFoundException(String.format("No existe ruleta con el ID: %d", ruletaId));
		}
		if(!ruleta.get().getEstado()) {
			throw new BadRequestException(String.format("la ruleta con el ID: %d ya está cerrada", ruletaId));
		}
		Boolean resultado = ruletaService.CerrarRuleta(ruleta.get());
		if (!resultado) {
			throw new NotFoundException(String.format("No se pudo cerrar la ruleta con el ID %d",ruletaId));
	
		}
		return new ResponseEntity<Ruleta>(ruleta.get(), HttpStatus.OK);

	}
	/**
	 *  Endpoint para realizar apuestas por numero
	 * @param ruletaId Id de la ruleta a la cual se va apostar
	 * @param monto a apostar
	 * @param numeroApuesta (del 1 al 36) numero para apostar
	 * @return Retorna el estado de la apuesta (GANADA O PERDIDA) 
	 * @throws NotFoundException en caso de no existir la ruleta con el id
	 * @throws RuletaNotOpenedException en caso de que la ruleta no esté abierta
	 * @throws BadRequestException en caso de apostar por un numero invalido o superar el monto maximo de apuestas
	 * @author LEPM
	 */
	
	/*public ResponseEntity<?> realizarApuestaNumero(@PathVariable Integer ruletaId,@PathVariable Double monto,@PathVariable Integer numeroApuesta){
		
		
		if (numeroApuesta<0 || numeroApuesta>36) {
			throw new BadRequestException(String.format("LOS NUMEROS PERMITIDOS SON DEL 0 AL 36"));
		}
		Optional<Ruleta> ruleta = ruletaService.buscarRuletaPorId(ruletaId);
		if (!ruleta.isPresent()) {
			throw new NotFoundException(String.format("No existe ruleta con el ID: %d", ruletaId));
		}
		if(!ruleta.get().getEstado()) {
			throw new RuletaNotOpenedException(String.format("La ruleta con el ID %d no ha sido abierta",ruletaId));
		}
		if(monto>10000) {
			throw new BadRequestException(String.format("EXCEDISTE EL MONTO MAXIMO DE APUESTA, EL MONTO MAXIMO ES 10,000"));
		}
		
		Optional<Apuesta> apuestaOptional = null;
		
		apuestaOptional = ruletaService.ApostarPorNumero(ruleta.get(), numeroApuesta, monto);
		
		
		return new ResponseEntity<Apuesta>(apuestaOptional.get(), HttpStatus.CREATED);
	}*/

	@PostMapping("/apuestaNumero/{ruletaId}/monto/{monto}/numeroApuesta/{numeroApuesta}")
	public ResponseEntity<?> realizarApuestaNumero(@RequestBody Apuesta apuesta){
		Integer numeroApuesta = apuesta.getNumeroApostado();
		Integer ruletaId = apuesta.getRuletaIds();
		Double monto = apuesta.getMonto();
		
		if (numeroApuesta<0 || numeroApuesta>36) {
			throw new BadRequestException(String.format("LOS NUMEROS PERMITIDOS SON DEL 0 AL 36"));
		}
		Optional<Ruleta> ruleta = ruletaService.buscarRuletaPorId(ruletaId);
		if (!ruleta.isPresent()) {
			throw new NotFoundException(String.format("No existe ruleta con el ID: %d", ruletaId));
		}
		if(!ruleta.get().getEstado()) {
			throw new RuletaNotOpenedException(String.format("La ruleta con el ID %d no ha sido abierta",ruletaId));
		}
		if(monto>10000) {
			throw new BadRequestException(String.format("EXCEDISTE EL MONTO MAXIMO DE APUESTA, EL MONTO MAXIMO ES 10,000"));
		}
		
		Optional<Apuesta> apuestaOptional = null;
		
		apuestaOptional = ruletaService.ApostarPorNumero(ruleta.get(), numeroApuesta, monto);
		
		
		return new ResponseEntity<Apuesta>(apuestaOptional.get(), HttpStatus.CREATED);
	}
	
	
	/**
	 *  Endpoint para realizar apuestas por color
	 * @param apuesta se recibe un objeto de tipo apuesta con el monto a apostar, el color de la apuesta(ROJO O NEGRO) y el id de la ruleta donde se quire apostar
	 * @return Retorna el estado de la apuesta (GANADA O PERDIDA) 
	 * @throws NotFoundException en caso de no existir la ruleta con el id
	 * @throws RuletaNotOpenedException en caso de que la ruleta no esté abierta
	 * @throws BadRequestException en caso de apostar por un color invalido o superar el monto maximo de apuestas
	 * @author LEPM
	 */

	@PostMapping("/apuestaColor/{ruletaId}/monto/{monto}/colorApuesta/{colorApuesta}")
	public ResponseEntity<?> realizarApuestaColor(@RequestBody Apuesta apuesta){
		String colorApuesta = apuesta.getColorApostado();
		Integer ruletaId = apuesta.getRuletaIds();
		
		if (!colorApuesta.equalsIgnoreCase("negro") && !colorApuesta.equalsIgnoreCase("rojo")) {
			throw new BadRequestException(String.format("DEBES ELEGIR UN COLOR ENTRE ROJO O NEGRO"));
		}
		
		Optional<Ruleta> ruleta = ruletaService.buscarRuletaPorId(ruletaId);
		if (!ruleta.isPresent()) {
			throw new NotFoundException(String.format("No existe ruleta con el ID: %d", ruletaId));
		}
		if(!ruleta.get().getEstado()) {
			throw new RuletaNotOpenedException(String.format("La ruleta con el ID %d no ha sido abierta",ruletaId));
		}
		if(apuesta.getMonto()>10000) {
			throw new BadRequestException(String.format("EXCEDISTE EL MONTO MAXIMO DE APUESTA, EL MONTO MAXIMO ES 10,000"));
		}
		Optional<Apuesta> apuestaOptional = null;
		
		apuestaOptional = ruletaService.ApostarPorColor(ruleta.get(), colorApuesta, apuesta.getMonto());
		
		
		return new ResponseEntity<Apuesta>(apuestaOptional.get(), HttpStatus.CREATED);

	}
	
	/**
	 *  Endpoint para listar las ruletas en el sistema
	 *  
	 * @return ResponseEntity con el listado de ruletas
	 * @throws NotFoundException en caso de que no exista ninguna ruleta
	 * @author LEPM
	 */
	@GetMapping("/listar")
	public ResponseEntity<?> buscarTodas() {
		List<Ruleta> ruletas = ruletaService.buscarTodos();
		if (ruletas.isEmpty()) {
			throw new NotFoundException("No hay ruletas en el sistema");
		}
		
		return new ResponseEntity<List<Ruleta>>(ruletas, HttpStatus.OK);
	}

}
