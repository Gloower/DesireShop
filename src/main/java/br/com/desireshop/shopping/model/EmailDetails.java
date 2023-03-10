package br.com.desireshop.shopping.model;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails extends Clientes{


    //Colocar o email que você quer colocar
    private String destinatario = getEmail();

    //Colocar o texto do email
    private String msgBody;

    //Colocar o assunto (subject)
    private String subject;

    //Colocar o anexo (attachment)
    private String attachment;
}
