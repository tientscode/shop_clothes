package com.tscode.shop_clothes.sevice.mail;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {

    String from;
    String to;
    String[] cc;
    String[] bcc;
    String subject;
    String body;
    String[] attachments;

    public MailInfo(String to, String subject, String body) {
        this.from = "LitWorld <poly@gmail.com>";
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
