package com.azienda.comunicazioni_aziendali.mapper;

import com.azienda.comunicazioni_aziendali.dto.request.ComunicazioniAziendaliRequest;
import com.azienda.comunicazioni_aziendali.dto.response.ComunicazioniAziendaliResponse;
import com.azienda.comunicazioni_aziendali.entities.ComunicazioniAziendali;
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
