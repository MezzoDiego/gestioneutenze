package it.prova.gestioneutenze.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestioneutenze.model.Utente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtenteDTOForUpdate {

	private Long id;

	@NotBlank(message = "{username.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String username;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "{dataDiNascita.notnull}")
	private LocalDate dataDiNascita;

	private RuoloDTO ruolo;

	public UtenteDTOForUpdate() {
	}

	public UtenteDTOForUpdate(Long id, String username, String nome, String cognome, LocalDate dataDiNascita) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public RuoloDTO getRuolo() {
		return ruolo;
	}

	public void setRuolo(RuoloDTO ruolo) {
		this.ruolo = ruolo;
	}

	public Utente buildUtenteModel(boolean includeIdRole) {
		Utente result = new Utente(this.id, this.username, this.nome, this.cognome, this.dataDiNascita);
		if (includeIdRole && ruolo != null)
			result.setRuolo(ruolo.buildRuoloModel());

		return result;
	}

	// niente password...
	public static UtenteDTOForUpdate buildUtenteDTOFromModel(Utente utenteModel) {
		UtenteDTOForUpdate result = new UtenteDTOForUpdate(utenteModel.getId(), utenteModel.getUsername(), utenteModel.getNome(),
				utenteModel.getCognome(), utenteModel.getDataDiNascita());

		if (utenteModel.getRuolo() != null)
			result.ruolo = RuoloDTO.buildRuoloDTOFromModel(utenteModel.getRuolo());

		return result;
	}

	public static List<UtenteDTO> createUtenteDTOListFromModelList(List<Utente> modelListInput) {
		return modelListInput.stream().map(utenteEntity -> {
			return UtenteDTO.buildUtenteDTOFromModel(utenteEntity);
		}).collect(Collectors.toList());
	}
	
}
