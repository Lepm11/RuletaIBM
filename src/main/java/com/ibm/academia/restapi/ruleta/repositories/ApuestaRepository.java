package com.ibm.academia.restapi.ruleta.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.ruleta.models.entities.Apuesta;

@Repository
public interface ApuestaRepository extends CrudRepository<Apuesta	, Integer> {

}
