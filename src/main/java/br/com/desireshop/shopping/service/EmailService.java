package br.com.desireshop.shopping.service;

import br.com.desireshop.shopping.model.EmailDetails;

public interface EmailService {

    String sendEmailWithAttachment (EmailDetails emailDetails);

}
