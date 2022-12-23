package it.prova.gestioneutenze.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestioneutenze.dto.RuoloDTO;
import it.prova.gestioneutenze.dto.UtenteDTO;
import it.prova.gestioneutenze.dto.UtenteDTOForUpdate;
import it.prova.gestioneutenze.model.Ruolo;
import it.prova.gestioneutenze.model.Utente;
import it.prova.gestioneutenze.security.dto.UtenteInfoJWTResponseDTO;
import it.prova.gestioneutenze.service.UtenteService;
import it.prova.gestioneutenze.web.api.exceptions.IdNotNullForInsertException;
import it.prova.gestioneutenze.web.api.exceptions.NotFoundException;
import it.prova.gestioneutenze.web.api.exceptions.PasswordNonCoincidonoException;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	// questa mi serve solo per capire se solo ADMIN vi ha accesso
	@GetMapping("/testSoloAdmin")
	public String test() {
		return "OK";
	}

	@GetMapping(value = "/userInfo")
	public ResponseEntity<UtenteInfoJWTResponseDTO> getUserInfo() {

		// se sono qui significa che sono autenticato quindi devo estrarre le info dal
		// contesto
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		// estraggo le info dal principal
		Utente utenteLoggato = utenteService.findByUsername(username);
		Ruolo ruolo = utenteLoggato.getRuolo();

		return ResponseEntity.ok(new UtenteInfoJWTResponseDTO(utenteLoggato.getNome(), utenteLoggato.getCognome(),
				utenteLoggato.getUsername(), RuoloDTO.buildRuoloDTOFromModel(ruolo)));
	}
	
	@GetMapping
	public List<UtenteDTO> listAll() {
		return UtenteDTO.createUtenteDTOListFromModelList(utenteService.listAllUtenti());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UtenteDTO createNew(@Valid @RequestBody UtenteDTO utenteInput) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (utenteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		if(!utenteInput.getPassword().equals(utenteInput.getConfermaPassword())) {
			throw new PasswordNonCoincidonoException("Le password non coincidono!");
		}

		Utente utenteInserito = utenteService.inserisciNuovo(utenteInput.buildUtenteModel(true));
		return UtenteDTO.buildUtenteDTOFromModel(utenteInserito);
	}
	
	@PutMapping("/{id}")
	public UtenteDTO update(@Valid @RequestBody UtenteDTOForUpdate utenteInput,
			@PathVariable(required = true) Long id) {
		Utente utente = utenteService.caricaSingoloUtente(id);

		if (utente == null)
			throw new NotFoundException("Utente not found con id: " + id);

		utenteInput.setId(id);
		Utente utenteAggiornato = utenteService.aggiorna(utenteInput.buildUtenteModel(true));
		return UtenteDTO.buildUtenteDTOFromModel(utenteAggiornato);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		utenteService.rimuovi(id);
	}
	
	@GetMapping("/{id}")
	public UtenteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Utente utente = utenteService.caricaSingoloUtente(id);

		if (utente == null)
			throw new NotFoundException("Utente not found con id: " + id);

		return UtenteDTO.buildUtenteDTOFromModel(utente);
	}
}
