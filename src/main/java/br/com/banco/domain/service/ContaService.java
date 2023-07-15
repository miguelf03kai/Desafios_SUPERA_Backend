package br.com.banco.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.banco.domain.model.Conta;
import br.com.banco.domain.repository.ContaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ContaService {
	private ContaRepository contaRepository;
	
	public Page<Conta> listarContas(Pageable pageable){
		return contaRepository.findAll(pageable);
	}
}
