package br.com.banco.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.repository.TransferenciaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TranferenciaService {
   @Autowired
   private TransferenciaRepository transferenciaRepository;
	
   public Page<Transferencia> buscarPeriodoOperador(OffsetDateTime dataInicio, OffsetDateTime dataFim, 
		   											String nomeOperadorTransacao, Pageable pageable) {
        return transferenciaRepository.findByPeriodoOperador(dataInicio, dataFim, nomeOperadorTransacao, pageable);
   }
}
