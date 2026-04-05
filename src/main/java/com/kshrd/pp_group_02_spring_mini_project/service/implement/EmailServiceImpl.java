package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final MailSender mailSender;

    @Override
    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message  = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("OTP  Verification");
        message.setText("Your OTP is : " + otp + "\n This OTP Valid for 5 minutes.");
        mailSender.send(message);
    }
}
