package com.project.grihini.core.onboarding.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {
    ADMIN(0),
    MERCHANT(1),
    USER(2);
    private Integer state;
}
