package br.com.banco.utils;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Conversao {
	
	  public static OffsetDateTime converteData(String data) {
		 	OffsetDateTime result = null;
		 	
			try {
				if (data != null && !data.isEmpty()) {
			        LocalDate inicioDate = LocalDate.parse(data);
			        result = inicioDate.atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			return result;
	  }
	  
	  public static Long converteContaId(String contaId) {
		 	Long result;
		 	
			try {
				if (contaId != null && !contaId.isEmpty()) {
			        result = Long.parseLong(contaId);
			        return result;
				}
				else 
					return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	  }
}
