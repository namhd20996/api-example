package com.example.assign.confirmtoken;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ConfirmationTokenMapper implements
        Function<ConfirmationToken, ConfirmationTokenDTO> {

    private final ModelMapper mapper;

    @Override
    public ConfirmationTokenDTO apply(ConfirmationToken confirmationToken) {
        return mapper.map(confirmationToken, ConfirmationTokenDTO.class);
    }

}
