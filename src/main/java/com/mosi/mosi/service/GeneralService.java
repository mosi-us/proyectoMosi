package com.mosi.mosi.service;

import com.mosi.mosi.MosiApplication;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.mosi.mosi.constantes.constante.*;

@Service
public class GeneralService {

    public Object getPropertiesEmail() throws IOException {
        Properties p = new Properties();
        Properties properties = new Properties();
        InputStream file = null;
        file = MosiApplication.class.getClassLoader().getResourceAsStream("application.properties");
        p.load(file);
        properties.put(SERVER_STMP,p.getProperty(SERVER_STMP));
        properties.put(REMITENTE_EMAIL,p.getProperty(REMITENTE_EMAIL));
        properties.put(PASSWORD_EMAIL,p.getProperty(PASSWORD_EMAIL));
        properties.put(AUTH_STMP,p.getProperty(AUTH_STMP));
        properties.put(STARTTLS,p.getProperty(STARTTLS));
        properties.put(PUERTO_STMP,587);
        properties.put(SMTP_SSL, CERTIFICADO_SSL);

        return properties;
    }

    public void enviarEmail(String desti,String asunto,String cuerpo) throws MessagingException, IOException {
        Address destinatario= new InternetAddress(desti);
        Properties properties =(Properties) this.getPropertiesEmail();

        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(properties.getProperty(REMITENTE_EMAIL)));
        message.addRecipient(Message.RecipientType.TO,destinatario );   //Se podrían añadir varios de la misma manera
        message.setSubject(asunto);
        message.setText(cuerpo);
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", properties.getProperty(REMITENTE_EMAIL), properties.getProperty(PASSWORD_EMAIL));
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
