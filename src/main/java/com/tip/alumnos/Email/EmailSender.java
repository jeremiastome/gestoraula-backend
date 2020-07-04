package com.tip.alumnos.Email;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.Properties;

public class EmailSender {

    private static EmailSender instance = null;

    private EmailSender() {
    }

    public static EmailSender getInstance() {
        if (instance == null) {
            instance = new EmailSender();
        }
        return instance;
    }

    public void sendEmail(Email mail) {

        try {

            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", mail.getUser());
            p.setProperty("mail.smtp.auth", "true");

            Session s = Session.getDefaultInstance(p, null);
            BodyPart text = new MimeBodyPart();
            text.setText(mail.getMessage());

            MimeMultipart multi = new MimeMultipart();
            multi.addBodyPart(text);

            MimeMessage message = new MimeMessage(s);
            message.setFrom(new InternetAddress(mail.getUser()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceiver()));
            message.setSubject(mail.getSubject());
            File input = new File("Template.html");
            Document doc = Jsoup.parse(input, "UTF-8", "");
            Element contenido = doc.select(".contenido").first();
            Element remover = doc.select(".remover").first();
            Element alta = doc.select(".alta").first();
            Element comunicacion = doc.select(".comunicacion").first();
            contenido.text(mail.getMessage());
            remover.text(mail.getSubject());
            alta.text(mail.getSubject());
            comunicacion.text(mail.getSubject());
            String template = doc.html();
            message.setContent(template, "text/html");

            Transport t = s.getTransport("smtp");
            t.connect(mail.getUser(), mail.getPassword());
            t.sendMessage(message, message.getAllRecipients());
            t.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}