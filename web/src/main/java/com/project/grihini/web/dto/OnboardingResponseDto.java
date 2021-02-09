package com.project.grihini.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class OnboardingResponseDto {

    private String responseCode;
    private String responseMsg;

    public OnboardingResponseDto() {
    }

}
