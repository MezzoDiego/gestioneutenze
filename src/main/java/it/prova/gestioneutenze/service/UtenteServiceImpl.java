package it.prova.gestioneutenze.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestioneutenze.model.Utente;
import it.prova.gestioneutenze.repository.utente.UtenteRepository;

@Service
@Transactional(readOnly = true)
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Utente> listAllUtenti() {
		return (List<Utente>) repository.findAll();
	}

	@Override
	public Utente caricaSingoloUtente(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Utente aggiorna(Utente utenteInstance) {
		Utente utenteReloaded = repository.findById(utenteInstance.getId()).orElse(null);
		if (utenteReloaded == null)
			throw new RuntimeException("Elemento non trovato");

		utenteReloaded.setNome(utenteInstance.getNome());
		utenteReloaded.setCognome(utenteInstance.getCognome());
		utenteReloaded.setUsername(utenteInstance.getUsername());
		utenteReloaded.setRuolo(utenteInstance.getRuolo());
		utenteReloaded.setDataDiNascita(utenteInstance.getDataDiNascita());
		return repository.save(utenteReloaded);

	}

	@Transactional
	@Override
	public Utente inserisciNuovo(Utente utenteInstance) {
		utenteInstance.setPassword(passwordEncoder.encode(utenteInstance.getPassword()));
		return repository.save(utenteInstance);

	}

	@Transactional
	@Override
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);

	}

	@Override
	public List<Utente> findByExample(Utente example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente findByUsername(String username) {
		return repository.findByUsername(username).orElse(null);
	}

}
