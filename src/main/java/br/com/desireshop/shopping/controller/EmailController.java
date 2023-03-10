package br.com.desireshop.shopping.controller;


import br.com.desireshop.shopping.model.EmailDetails;
import br.com.desireshop.shopping.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired private EmailService emailService;

    @PostMapping("/sendEmailWithAttachment")
    public String sendEmailWithAttachment(
            @RequestBody EmailDetails emailDetails){

        String status = emailService.sendEmailWithAttachment(emailDetails);;

        return status;
    }
}
