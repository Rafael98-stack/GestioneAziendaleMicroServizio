package com.azienda.dipendenti.services;


import com.azienda.dipendenti.entities.Timbratura;
import com.azienda.dipendenti.repositories.TimbraturaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
public class TimbraturaReportJob implements Job
{
    @Autowired
    TimbraturaService timbraturaService;
    @Autowired
    TimbraturaRepository timbraturaRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    Scheduler scheduler;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        List<Timbratura> timbrature = timbraturaRepository.findBydata_corrente(LocalDate.now());

        if (timbrature.isEmpty()) {
            System.out.println("Nessuna timbratura registrata per oggi.");
            return;
        }

        try {
            String filePath = generateCSV(timbrature);
            sendEmailWithAttachment("pellegrininanda96@gmail.com", filePath);
            System.out.println("Report timbrature inviato con successo!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailWithAttachment(String to, String filePath) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Report Timbrature Giornaliero");
        helper.setText("In allegato il report delle timbrature giornaliere.");

        FileSystemResource file = new FileSystemResource(new File(filePath));
        helper.addAttachment(file.getFilename(), file);

        javaMailSender.send(message);
    }

    public void timbratureGiornaliereScheduler() throws SchedulerException {
        JobKey jobKey = new JobKey("timbrature giornaliere", "emailJobs");
        if (!scheduler.checkExists(jobKey)) {
            JobDetail jobDetail = JobBuilder.newJob(TimbraturaReportJob.class)
                    .withIdentity("timbrature giornaliere", "emailJobs")
                    .storeDurably()
                    .build();
            Trigger trigger = buildJobTrigger(jobDetail);
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }

    private Trigger buildJobTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(14, 51))
                .build();
    }

    public String generateCSV(List<Timbratura> timbrature) {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID, DipendenteID, Ingresso, Uscita, Inizio Pausa, Fine Pausa\n");
        for (Timbratura t : timbrature) {
            csvBuilder.append(t.getId()).append(",")
                    .append(t.getDipendente()).append(",")
                    .append(t.getOrario_entrata()).append(",")
                    .append(t.getInizio_pranzo()).append(",")
                    .append(t.getFine_pranzo()).append(",")
                    .append(t.getUscita())
                    .append("\n");
        }
        return csvBuilder.toString();
    }

    @PostConstruct
    public void init() throws SchedulerException {
        timbratureGiornaliereScheduler();
    }
}
