package fr.uca.cdr.skillful_network.security;

import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;

public class SendMail {
	
	private final static String MAILER_VERSION = "Java";
	  
	public static boolean envoyerMailSMTP(String mailAddress, String codeAutoGen) {
	    boolean result = false;
	    boolean debug = false;
	    String serveur = "127.0.0.1"; // doit obligatoirement correspondre à celle du server local à installer sur le pc !
	    String messageText = "Bonjour, \n \n Vous trouverez ci-dessous le code permettant de terminer votre inscription � notre site POEC.\n \n";
	    messageText += codeAutoGen;
	    messageText += "\n \n Cordialement";
	    try {
	      Properties prop = System.getProperties();
	      prop.put("mail.smtp.host", serveur);
	      Session session = Session.getDefaultInstance(prop,null);
	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress("user1@sendmail.com"));
	      InternetAddress[] internetAddresses = new InternetAddress[1];
	      internetAddresses[0] = new InternetAddress(mailAddress);
	      message.setRecipients(Message.RecipientType.TO,internetAddresses);
	      message.setSubject("Inscription site POEC");
	      message.setText(messageText);
	      message.setHeader("X-Mailer", MAILER_VERSION);
	      message.setSentDate(new Date());
	      session.setDebug(debug);
	      Transport.send(message);
	      result = true;
	    } catch (AddressException e) {
	      e.printStackTrace();
	    } catch (MessagingException e) {
	      e.printStackTrace();
	    }
	    return result;
	  }

}
