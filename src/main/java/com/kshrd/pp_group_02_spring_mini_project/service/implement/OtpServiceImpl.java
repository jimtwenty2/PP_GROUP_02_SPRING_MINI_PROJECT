package com.kshrd.pp_group_02_spring_mini_project.service.implement;

import com.kshrd.pp_group_02_spring_mini_project.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final RedisTemplate<String, String> redisTemplate;
    private static final long OTP_VALIDITY = 300;
    private static final int MAX_RESEND = 3;
    @Override
    public String generateOtp(String email) {
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        redisTemplate.opsForValue().set("otp:" + email, otp, OTP_VALIDITY, TimeUnit.SECONDS);
        redisTemplate.opsForValue().setIfAbsent("otp:resend:" + email, "0", OTP_VALIDITY, TimeUnit.SECONDS);
        return otp;
    }

    @Override
    public boolean verifyOtp(String email, String inputOtp) {
        String otp = redisTemplate.opsForValue().get("otp:"+email);
        if(otp != null && otp.equals(inputOtp)){
            redisTemplate.delete("otp:" + email);
            redisTemplate.delete("otp:resend:" + email);
            return true;
        }
        return false;
    }

    @Override
    public String resendOtp(String email) {
        String counterKey = "otp:resend" + email;
        String value = redisTemplate.opsForValue().get(counterKey);
        int count = value != null ? Integer.parseInt(value) : 0;
        if (count >= MAX_RESEND) {
            throw new RuntimeException("Max resend reached");
        }
        redisTemplate.opsForValue().increment(counterKey);
        redisTemplate.expire(counterKey, OTP_VALIDITY, TimeUnit.SECONDS);
        return generateOtp(email);
    }
}
