package com.azienda.comunicazioni_aziendali.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ComunicazioniProducer {

    @Autowired
    private KafkaTemplate<String, ComunicazioneAziendaleMessage> kafkaTemplate;

    public void sendConfermaOrdine(ComunicazioneAziendaleMessage comunicazioneAziendaleMessage) {
        Message<ComunicazioneAziendaleMessage> message = MessageBuilder
                .withPayload(comunicazioneAziendaleMessage)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();
        kafkaTemplate.send(message);
    }

}
