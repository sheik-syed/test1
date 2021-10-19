package com.ecomshop.deskplus.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Author: Sheik Syed Ali
 * Date: 26 Sep 2021
 */
@Service
public class MailService {

    private Environment environment;

    private JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    @Value("${deskplus.base-url}")
    private String baseUrl;

    public MailService(Environment environment, JavaMailSender mailSender, TemplateEngine templateEngine){
        this.environment = environment;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }


    public void sendAccountActivationMail(String name, String mailAddress, String activationKey){
        String templateName = "registration";
        String logoImage = "templates/images/spring.png";
        String pngMime = "image/png";
        String mailSubject = "Registration Confirmation";

        String confirmationUrl = baseUrl + "activate/" + activationKey;
        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");

        try{
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper email;
            email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            email.setTo(mailAddress);
            email.setSubject(mailSubject);
            email.setFrom(new InternetAddress(mailFrom, mailFromName));

            Context ctx = new Context(LocaleContextHolder.getLocale());
            ctx.setVariable("email", mailAddress);
            ctx.setVariable("name", name);
            ctx.setVariable("springLogo", logoImage);
            ctx.setVariable("url", confirmationUrl);

            String htmlContent = this.templateEngine.process(templateName, ctx);

            email.setText(htmlContent, true);

            ClassPathResource clr = new ClassPathResource(logoImage);

            email.addInline("springLogo", clr, pngMime);

            mailSender.send(mimeMessage);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void sendUserCreatedMail(String name, String mailAddress, String password){
        String templateName = "mail/user-created";
        String logoImage = "templates/images/spring.png";
        String pngMime = "image/png";
        String mailSubject = "User Created";

        String loginUrl = baseUrl + "login";
        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");

        try{
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper email;
            email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            email.setTo(mailAddress);
            email.setSubject(mailSubject);
            email.setFrom(new InternetAddress(mailFrom, mailFromName));

            Context ctx = new Context(LocaleContextHolder.getLocale());
            ctx.setVariable("email", mailAddress);
            ctx.setVariable("name", name);
            ctx.setVariable("springLogo", logoImage);
            ctx.setVariable("url", loginUrl);
            ctx.setVariable("password", password);

            String htmlContent = this.templateEngine.process(templateName, ctx);

            email.setText(htmlContent, true);

            ClassPathResource clr = new ClassPathResource(logoImage);

            email.addInline("springLogo", clr, pngMime);

            mailSender.send(mimeMessage);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
