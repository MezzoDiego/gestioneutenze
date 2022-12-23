package it.prova.gestioneutenze.service;

import java.util.List;

import it.prova.gestioneutenze.model.Utente;

public interface UtenteService {

	public List<Utente> listAllUtenti();

	public Utente caricaSingoloUtente(Long id);

	public Utente aggiorna(Utente utenteInstance);

	public Utente inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Long idToRemove);

	public List<Utente> findByExample(Utente example);

	public Utente findByUsername(String username);

}
