package br.com.banco.domain.service;

import java.text.DecimalFormat;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.banco.domain.model.Conta;
import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.repository.TransferenciaRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TransferenciaService {
   @Autowired
   private TransferenciaRepository transferenciaRepository;
	
   public Page<Transferencia> buscarPeriodoOperador(OffsetDateTime dataInicio, OffsetDateTime dataFim, 
		   											String nomeOperadorTransacao, Long contaId, Pageable pageable) {
        return transferenciaRepository.
        		findByPeriodoOperador(dataInicio, dataFim, nomeOperadorTransacao, contaId, pageable);
   }
   
   public double buscarOperadorSaldoTotal(String nomeOperadorTransacao, Long contaId) {
	   	double saldoTotal = 0.0;
	   	
	   	for (Transferencia t : transferenciaRepository.
	   							findByPeriodoOperador(null, null, nomeOperadorTransacao, contaId, null).getContent()) 
	   	{
		   saldoTotal += t.getValor();
		}
	   
	   	return Double.valueOf(new DecimalFormat("#.##").format(saldoTotal));
   }
   
   public double buscarPeriodoOperadorSaldoPeriodo(OffsetDateTime dataInicio, OffsetDateTime dataFim, 
													String nomeOperadorTransacao, Long contaId, Pageable pageable) {
	   double saldoPeriodo = 0.0;
	   
	   for (Transferencia t : transferenciaRepository.
			   					findByPeriodoOperador(dataInicio, dataFim, nomeOperadorTransacao, contaId, pageable).getContent()) {
			saldoPeriodo += t.getValor();
	   }
						   
	   return Double.valueOf(new DecimalFormat("#.##").format(saldoPeriodo));
   }
}
