package com.project.grihini.core.onboarding.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum PanelUserStatus {
    INACTIVE(0),
    ACTIVE(1);
    private Integer state;
}
