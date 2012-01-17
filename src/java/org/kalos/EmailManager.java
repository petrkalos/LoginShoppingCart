/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kalos;

/**
 *
 * @author Petros Kalos
 */
// File Name EmailManager.java
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailManager {

    private static final String EMAIL_PATTERN = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
    
    
    public static void send(String to) {

        // Sender's email ID needs to be mentioned
        String from = "kalos@inf.uth.gr";

        // Assuming you are sending email from localhost
        String host = "mail.uth.gr";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Shopping cart by Petros Kalos");

            // Now set the actual message
            message.setText("Welcome to my application");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            return;
        }
    }

    /**
     * Validate hex with regular expression
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean validate(final String hex) {
        Matcher matcher = pattern.matcher(hex);
        return !matcher.matches();
    }
}
