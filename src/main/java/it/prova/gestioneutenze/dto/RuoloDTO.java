package it.prova.gestioneutenze.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestioneutenze.model.Ruolo;
import it.prova.gestioneutenze.model.Utente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuoloDTO {

	private Long id;
	private String descrizione;
	
	public RuoloDTO() {
		// TODO Auto-generated constructor stub
	}

	public RuoloDTO(Long id, String descrizione) {
		this.id = id;
		this.descrizione = descrizione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Ruolo buildRuoloModel() {
		return new Ruolo(this.id, this.descrizione);
	}

	public static RuoloDTO buildRuoloDTOFromModel(Ruolo ruoloModel) {
		return new RuoloDTO(ruoloModel.getId(), ruoloModel.getDescrizione());
	}

	public static List<RuoloDTO> createRuoloDTOListFromModelList(List<Ruolo> modelListInput) {
		return modelListInput.stream().map(ruoloEntity -> {
			return RuoloDTO.buildRuoloDTOFromModel(ruoloEntity);
		}).collect(Collectors.toList());
	}

}
