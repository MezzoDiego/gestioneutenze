package it.prova.gestioneutenze.repository.utente;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestioneutenze.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
	
	Optional<Utente> findByUsername(String username);
	
	Utente findByUsernameAndPassword(String username, String password);
	
}
