package com.azienda.notifications.notification;

import com.azienda.notifications.kafka.comunicazione.ComunicazioneConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Notification {
    @Id
    private String id;
    private LocalDateTime notificationTime;
    private ComunicazioneConfirmation comunicazioneConfirmation;
}