package br.com.banco.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.banco.domain.model.Conta;

public interface ContaRepository extends PagingAndSortingRepository<Conta, Long>{}
