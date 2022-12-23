package it.prova.gestioneutenze;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestioneutenze.model.Ruolo;
import it.prova.gestioneutenze.model.Utente;
import it.prova.gestioneutenze.service.RuoloService;
import it.prova.gestioneutenze.service.UtenteService;

@SpringBootApplication
public class GestioneutenzeApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(GestioneutenzeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizione(Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo(Ruolo.ROLE_ADMIN));
		}

		if (ruoloServiceInstance.cercaPerDescrizione(Ruolo.ROLE_CLASSIC_USER) == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo(Ruolo.ROLE_CLASSIC_USER));
		}

		// a differenza degli altri progetti cerco solo per username perche' se vado
		// anche per password ogni volta ne inserisce uno nuovo, inoltre l'encode della
		// password non lo
		// faccio qui perche gia lo fa il service di utente, durante inserisciNuovo
		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", LocalDate.now());
			admin.setRuolo(ruoloServiceInstance.cercaPerDescrizione(Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = new Utente("user", "user", "Antonio", "Verdi", LocalDate.now());
			classicUser.setRuolo(ruoloServiceInstance.cercaPerDescrizione(Ruolo.ROLE_CLASSIC_USER));
			utenteServiceInstance.inserisciNuovo(classicUser);
		}

		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente classicUser1 = new Utente("user1", "user1", "Antonioo", "Verdii", LocalDate.now());
			classicUser1.setRuolo(ruoloServiceInstance.cercaPerDescrizione(Ruolo.ROLE_CLASSIC_USER));
			utenteServiceInstance.inserisciNuovo(classicUser1);
		}

	}

}
