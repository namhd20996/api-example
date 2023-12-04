package com.example.assign.validation;

import com.example.assign.exception.ApiRequestException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
public class ValidationHandleImpl implements ValidationHandle {
    @Override
    public void handleValidate(Errors errors) {
        if (errors.hasErrors()) {
            List<String> list = errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            throw new ApiRequestException(list.toString());
        }
    }
}
