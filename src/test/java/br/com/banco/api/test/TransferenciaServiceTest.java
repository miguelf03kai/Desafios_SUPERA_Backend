package br.com.banco.api.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.banco.domain.model.Transferencia;
import br.com.banco.domain.service.TransferenciaService;

@SpringBootTest
public class TransferenciaServiceTest {
	@Autowired
	TransferenciaService transferenciaService;
	
	@Test
	void testBuscarPeriodoPaginado() {
		Pageable pageable = PageRequest.of(0, 4);
		Page<Transferencia> obj = transferenciaService
								  .buscarPeriodoOperador(null, null, null, pageable);

		assertEquals(4, obj.getContent().size());
	}
}
