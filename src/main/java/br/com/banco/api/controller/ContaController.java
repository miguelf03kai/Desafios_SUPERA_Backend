package br.com.banco.api.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.domain.model.Conta;
import br.com.banco.domain.service.ContaService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:3000"})
@AllArgsConstructor
@RestController
@RequestMapping("/conta")
public class ContaController {

	private ContaService contaService;

	@GetMapping
	public List<Conta> listarContas(Pageable pageable) {
		return contaService.listarContas(pageable).getContent();
	}
}
