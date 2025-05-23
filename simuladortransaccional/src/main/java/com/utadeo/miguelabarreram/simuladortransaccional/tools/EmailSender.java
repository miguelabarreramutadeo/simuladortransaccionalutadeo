package com.utadeo.miguelabarreram.simuladortransaccional.tools;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Controller
public class EmailSender {

	private final JavaMailSender javaMailSender;

	public EmailSender() {
		this.javaMailSender = new JavaMailSenderImpl();
	}

	public void sendHtmlMail(String to, String cc, String bcc, String subject, String body) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(to);

		if (null != cc && !cc.isEmpty())
			helper.setCc(cc);

		if (null != bcc && !bcc.isEmpty())
			helper.setBcc(bcc);

		helper.setSubject(subject);
		helper.setText(body, true);
		javaMailSender.send(mimeMessage);
	}
}
