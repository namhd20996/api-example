package com.example.assign.confirmtoken;

import com.example.assign.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepo confirmationTokenRepo;

    private final ConfirmationTokenMapper confirmationTokenMapper;

    @Override
    public ConfirmationTokenDTO findByToken(String token) {
        return confirmationTokenRepo.findByToken(token)
                .map(confirmationTokenMapper)
                .orElseThrow(() -> new ApiRequestException("Token not found!.."));
    }

    @Override
    public void setConfirmAt(String token) {
        confirmationTokenRepo.updateConfirmAt(token, LocalDateTime.now());
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepo.save(token);
    }
}
