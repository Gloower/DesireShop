package br.com.desireshop.shopping.service;

import br.com.desireshop.shopping.model.EmailDetails;
import jakarta.mail.MessagingException;

public interface EmailService {

    String sendEmailWithAttachment (EmailDetails emailDetails);

}
