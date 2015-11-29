package pe.org.cnl.gestiondoc.util;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
 
public class Mail {

	private static final Logger logger = Logger.getLogger(Mail.class );
	
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 
	 * 
	 * @param from quien envia el email
	 * @param to para quien va dirigido el email 
	 * @param cc con copia a 
	 * @param subject asunto
	 * @param msg mensaje
	 */
	public void sendMail(String from, String to, String cc, String subject,	String msg) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			if( !Utiles.nullToBlank( cc ).equals(""))
				helper.setCc(cc);
			helper.setSubject(subject);
			helper.setText(msg, true);
			mailSender.send(message);
			logger.debug("mensaje enviado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMailWithAttachment(String from, String to, String cc, String subject,	String msg, String attachmentFilename, InputStreamSource file ) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			
			helper.addAttachment(attachmentFilename, file, "application/pdf");
			if( !Utiles.nullToBlank( cc ).equals(""))
				helper.setCc(cc);
			helper.setSubject(subject);
			helper.setText(msg, true);
			mailSender.send(message);
			logger.debug("mensaje enviado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail(String from, String to[], String cc[], String bcc[], String subject,	String msg) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setCc(cc);
			if(bcc!=null)helper.setBcc(bcc);
			helper.setSubject(subject);
			helper.setText(msg, true);
			mailSender.send(message);
			logger.debug("mensaje enviado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
			
}