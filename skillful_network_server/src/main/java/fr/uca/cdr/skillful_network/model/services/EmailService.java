package fr.uca.cdr.skillful_network.model.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	 @Autowired
     public JavaMailSender emailSender;
	 
    @Async
	public void sendEmail(String email , String codeAutoGen) {
   	 String messageText = "Bonjour, \n \n Vous trouverez ci-dessous le code permettant de terminer votre inscription � notre site POEC.\n \n";
	    messageText += codeAutoGen;
	    messageText += "\n \n Cordialement";
       // Create a Simple MailMessage.
       SimpleMailMessage message = new SimpleMailMessage();
        
       message.setTo(email);
       message.setSubject("Inscription site POEC");
       message.setSentDate(new Date());
       message.setText(messageText);

       // Send Message!
       emailSender.send(message);
   }
}
