package com.ibm.academia.restapi.ruleta.datos;

import java.util.Optional;
import java.util.Random;

import com.ibm.academia.restapi.ruleta.models.entities.Apuesta;
import com.ibm.academia.restapi.ruleta.models.entities.Ruleta;
import com.ibm.academia.restapi.ruleta.repositories.ApuestaRepository;

public class DatosDummy {
	
	public static Ruleta ruleta01() {
		return new Ruleta();	
		}
	public static Ruleta ruleta02() {
		return new Ruleta();	
		}

	public static Ruleta ruleta03() {
		return new Ruleta();	
		}

	public static Integer girarRuleta() {
		Random random = new Random();
		int limiteMaximo = 37;
		return random.nextInt(limiteMaximo);
	}


}
