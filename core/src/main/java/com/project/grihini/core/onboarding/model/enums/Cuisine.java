package com.project.grihini.core.onboarding.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum Cuisine {
    BIHARI(0),
    BENGALI(1),
    SOUTH_INDIAN(2),
    NORTH_INDIAN(3);
    private Integer status;
}
