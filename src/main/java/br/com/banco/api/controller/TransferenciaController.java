package br.com.banco.api.controller;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.service.TransferenciaService;
import br.com.banco.utils.Conversao;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:3000"}, exposedHeaders = "X-Total-Count")
@AllArgsConstructor
@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	@GetMapping("/periodo")
    public ResponseEntity<Map<String, Object>> buscarPeriodoOperador(
    		 @RequestParam(required = false) @DateTimeFormat(pattern= "yyyy-MM-dd") String dataInicio,
    	     @RequestParam(required = false) @DateTimeFormat(pattern= "yyyy-MM-dd") String dataFim,
    	     @RequestParam(required = false) String operador,
             Pageable pageable) {

		OffsetDateTime inicio = Conversao.converteData(dataInicio);
		OffsetDateTime fim = Conversao.converteData(dataFim);
		
        Page<Transferencia> transferencias = transferenciaService
        									 .buscarPeriodoOperador(inicio, fim, operador, pageable);

        double saldoTotal = 0.0;
        double saldoPeriodo = 0.0;
        long totalCount = 0;

        for (Transferencia t : transferenciaService
        					   .buscarPeriodoOperador(inicio, fim, operador, null)
					           .getContent()) {
        	saldoPeriodo += t.getValor();
        }
        
        for (Transferencia t : transferenciaService.buscarPeriodoOperador(null, null, null, null)
        										    .getContent()) {
        	saldoTotal += t.getValor();
        }
        
        // Obtem quantidade total de movimentações
        totalCount = transferencias.getTotalElements();

        Map<String, Object> response = new HashMap<>();
        response.put("transferencias", transferencias.getContent());
        response.put("saldoPeriodo", saldoPeriodo);
        response.put("saldoTotal", saldoTotal);

        // Referencia para controle de paginação do front end
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalCount));

        return ResponseEntity.ok()
                .headers(headers)
                .body(response);
    }
}
