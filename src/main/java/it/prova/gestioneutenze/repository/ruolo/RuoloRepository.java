package it.prova.gestioneutenze.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestioneutenze.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {
	Ruolo findByDescrizione(String descrizione);
}
