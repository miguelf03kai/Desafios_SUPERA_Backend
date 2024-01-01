package br.com.banco.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Transferencia;

@Repository
public interface TransferenciaRepository extends PagingAndSortingRepository<Transferencia,Long>{
	
	@Query("SELECT t FROM transferencia t WHERE (:conta IS NULL OR t.conta.idConta = :conta) AND "+
		   "(:startDate IS NULL OR t.dataTransferencia >= :startDate) AND (:endDate IS NULL OR t.dataTransferencia <= :endDate)"+
		   " AND (COALESCE(:operador, '') = '' OR LOWER(t.nomeOperadorTransacao) LIKE LOWER(CONCAT('%', :operador , '%')))")
	Page<Transferencia> findByPeriodoOperador(
			 @Param("startDate") OffsetDateTime dataInicio,
		     @Param("endDate") OffsetDateTime dataFim,
		     @Param("operador") String nomeOperadorTransacao,
		     @Param("conta") Long contaId,
		     Pageable pageable);
}
