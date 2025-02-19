package com.azienda.dipendenti.services;


import com.azienda.dipendenti.controllers.TimbraturaController;
import com.azienda.dipendenti.entities.Timbratura;
import com.azienda.dipendenti.repositories.TimbraturaRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TimbraturaReportJob implements Job
{
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    TimbraturaRepository timbraturaRepository;
    @Autowired


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        List<Timbratura> timbrature = timbraturaRepository.findByOrarioInizioBetween(
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atTime(23, 59, 59)
        );

        if (timbrature.isEmpty()) {
            System.out.println("Nessuna timbratura registrata per oggi.");
            return;
        }

        try {
            String filePath = csvGeneratorService.generateCsv(timbrature);
            sendEmailWithAttachment("pellegrininanda96@gmail.com", filePath);
            System.out.println("Report timbrature inviato con successo!");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailWithAttachment(String to, String filePath) throws MessagingException
    {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Report Timbrature Giornaliero");
        helper.setText("In allegato il report delle timbrature giornaliere.");

        FileSystemResource file = new FileSystemResource(new File(filePath));
        helper.addAttachment(file.getFilename(), file);

        mailSender.send(message);
    }
    }



