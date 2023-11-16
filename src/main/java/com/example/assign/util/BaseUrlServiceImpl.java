package com.example.assign.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseUrlServiceImpl implements BaseUrlService {

    private final HttpServletRequest request;

    public String getBaseUrl() {
        return request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()));
    }
}
