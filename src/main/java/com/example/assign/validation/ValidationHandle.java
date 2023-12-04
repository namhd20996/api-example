package com.example.assign.validation;

import org.springframework.validation.Errors;

public interface ValidationHandle {

    void handleValidate(Errors errors);
}
