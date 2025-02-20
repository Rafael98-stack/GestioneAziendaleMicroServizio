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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
        List<Timbratura> timbrature = timbraturaRepository.findBydataCorrente(LocalDate.now());

        if (timbrature.isEmpty()) {
            System.out.println("Nessuna timbratura registrata per oggi.");
            return;
        }

        try {
            String filePath = generateCSV(timbrature);
            //sendEmailWithAttachment("localhost:1025", filePath);
            sendEmailWithAttachment("pellegrininanda96@gmail.com", filePath);
            sendEmailWithAttachment("fggabrielito@gmail.com", filePath);
            System.out.println("Report timbrature inviato con successo!");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailWithAttachment(String to, String filePath) throws MessagingException, IOException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Report Timbrature Giornaliero");
        helper.setText("In allegato il report delle timbrature giornaliere.");

        Path tempFile = Files.createTempFile("timbrature", ".csv");
        Files.write(tempFile, filePath.getBytes());
        FileSystemResource file = new FileSystemResource(tempFile.toFile());
        helper.addAttachment(file.getFilename(), file);

        javaMailSender.send(message);
        tempFile.toFile().deleteOnExit();
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
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * ?"))
               // .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(14, 53))
                .build();
    }

    public String generateCSV(List<Timbratura> timbrature) {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID, DipendenteID, Ingresso, Uscita, Inizio Pausa, Fine Pausa\n");
        for (Timbratura t : timbrature) {
            csvBuilder.append(t.getId()).append(",")
                    .append(t.getDipendente().getId()).append(",")
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
