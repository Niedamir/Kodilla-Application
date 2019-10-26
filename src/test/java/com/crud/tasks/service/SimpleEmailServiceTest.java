package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

	@InjectMocks
	private SimpleEmailService simpleEmailService;

	@Mock
	private JavaMailSender javaMailSender;

	@Test
	public void send() {
		//Given
		Mail mail = new Mail ("test@test.com", "","Test", "Test Message");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mail.getMailTo());
		mailMessage.setCc(mail.getToCc());
		mailMessage.setSubject(mail.getSubject());
		mailMessage.setText(mail.getMessage());
		//When
		simpleEmailService.send(mail);
		//Then
		verify(javaMailSender, times(1)).send(mailMessage);
	}
	@Test
	public void sendMultiply() {
		//Given
		Mail mail1 = new Mail ("test@test.com", "","Test", "Test Message");
		Mail mail2 = new Mail ("test2@tes.com", "", "Test", "Test Message");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mail1.getMailTo());
		mailMessage.setCc(mail1.getToCc());
		mailMessage.setSubject(mail1.getSubject());
		mailMessage.setText(mail1.getMessage());
		mailMessage.setTo(mail2.getMailTo());
		mailMessage.setCc(mail2.getToCc());
		mailMessage.setSubject(mail2.getSubject());
		mailMessage.setText(mail2.getMessage());
		//When
		simpleEmailService.send(mail1);
		simpleEmailService.send(mail2);
		//Then
		verify(javaMailSender, times(1)).send(mailMessage);
	}
	@Test
	public void sendCC() {
		//Given
		Mail mail = new Mail ("test@test.com", "CC@test.com","Test", "Test Message");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mail.getMailTo());
		mailMessage.setCc(mail.getToCc());
		mailMessage.setSubject(mail.getSubject());
		mailMessage.setText(mail.getMessage());
		//When
		simpleEmailService.send(mail);
		//Then
		verify(javaMailSender, times(1)).send(mailMessage);
	}
	@Test
	public void sendEmptyMail() {
		//Given
		Mail mail = new Mail ("test@test.com", " ",null, null);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mail.getMailTo());
		mailMessage.setCc(mail.getToCc());
		mailMessage.setSubject(mail.getSubject());
		mailMessage.setText(mail.getMessage());
		//When
		simpleEmailService.send(mail);
		//Then
		verify(javaMailSender, times(1)).send(mailMessage);
	}
}