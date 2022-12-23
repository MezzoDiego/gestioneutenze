package it.prova.gestioneutenze.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestioneutenze.dto.RuoloDTO;
import it.prova.gestioneutenze.service.RuoloService;

@RestController
@RequestMapping("/api/ruolo")
public class RuoloController {

	@Autowired
	private RuoloService ruoloService;

	@GetMapping
	public List<RuoloDTO> listAll() {
		return RuoloDTO.createRuoloDTOListFromModelList(ruoloService.listAll());
	}

}
