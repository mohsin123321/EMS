package com.domain.springframework.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String code;
    private long timestamp;
}
