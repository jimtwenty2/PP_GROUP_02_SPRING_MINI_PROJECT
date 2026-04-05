package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService  {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    public void sendOtpEmail(String to, String otpCode, String username) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            context.setVariable("username", username);
            context.setVariable("otpCode", otpCode);
            String html = templateEngine.process("otp-mail", context);
            helper.setTo(to);
            helper.setSubject("Your OTP Code");
            helper.setText(html, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}