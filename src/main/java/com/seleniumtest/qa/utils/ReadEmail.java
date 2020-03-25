package com.seleniumtest.qa.utils;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ReadEmail {

    public static HashMap email() throws MessagingException, IOException {

        Properties props = System.getProperties();
        props.put("mail.host", "smtp.dummydomain.com");
        props.put("mail.transport.protocol", "smtp");

        Session mailSession = Session.getDefaultInstance(props, null);
        InputStream source = new FileInputStream(ApplicationProperties.INSTANCE.getReferenceEmail());
        MimeMessage message = new MimeMessage(mailSession, source);

        HashMap<String, String> email = new HashMap<String, String>();
        email.put("subject", message.getSubject());
        email.put("from", message.getFrom()[0].toString());

        return email;
    }
}