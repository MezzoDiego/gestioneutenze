package it.prova.gestioneutenze.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.gestioneutenze.dto.RuoloDTO;
import it.prova.gestioneutenze.model.Ruolo;

public class UtenteInfoJWTResponseDTO {

	private String nome;
	private String cognome;
	private String type = "Bearer";
	private String username;
	private RuoloDTO role;

	public UtenteInfoJWTResponseDTO(String nome, String cognome, String username, RuoloDTO role) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.role = role;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public RuoloDTO getRole() {
		return role;
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
}