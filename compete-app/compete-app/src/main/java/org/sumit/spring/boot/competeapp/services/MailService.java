package org.sumit.spring.boot.competeapp.services;

public interface MailService {
	boolean sendMail(String from, String to, String subject, String message);
}
