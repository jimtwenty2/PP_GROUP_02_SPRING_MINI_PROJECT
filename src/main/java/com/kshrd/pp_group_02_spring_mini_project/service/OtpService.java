package com.kshrd.pp_group_02_spring_mini_project.service;

public interface OtpService {
    String generateOtp(String email);
    boolean verifyOtp(String email, String inputOtp);
    String resendOtp(String email);
}
