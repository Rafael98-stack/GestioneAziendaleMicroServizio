package com.azienda.notifications.notification;

import com.azienda.notifications.kafka.comunicazione.ComunicazioneConfirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationConsumer {
    @Autowired
    private NotificationRepository notificationRepository;

    @KafkaListener(topics = "comunicazione-topic", groupId = "notification-group")
    public void consumeComunicazioneNotification (ComunicazioneConfirmation comunicazioneConfirmation) {
        notificationRepository.save(
                Notification.builder()
                        .notificationTime(LocalDateTime.now())
                        .comunicazioneConfirmation(comunicazioneConfirmation)
                        .build()
        );
        /*TODO inviare la email di conferma*/
        System.out.println("comunica?!");
    }
}