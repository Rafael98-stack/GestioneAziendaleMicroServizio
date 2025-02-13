package com.azienda.newses.mappers;

import com.azienda.newses.ComunicazioniAziendaliResponse;
import com.azienda.newses.entities.ComunicazioniAziendali;
import com.azienda.newses.request.ComunicazioniAziendaliRequest;
import org.springframework.stereotype.Service;

@Service
public class ComunicazioniAziendaliMapper
{
  public ComunicazioniAziendaliResponse toResponse(ComunicazioniAziendali comunicazioniAziendali)
  {
      return ComunicazioniAziendaliResponse
              .builder()
              .Id(comunicazioniAziendali.getId())
              .titolo(comunicazioniAziendali.getTitolo())
              .contenuto(comunicazioniAziendali.getContenuto())
              .build();
  }

  public ComunicazioniAziendali toEntity (ComunicazioniAziendaliRequest request)
  {
      return ComunicazioniAziendali
              .builder()
              .titolo(request.titolo())
              .contenuto(request.contenuto())
              .build();
  }
}
