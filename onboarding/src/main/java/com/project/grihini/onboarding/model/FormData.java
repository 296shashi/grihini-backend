package com.project.grihini.onboarding.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.grihini.core.onboarding.model.enums.Cuisine;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormData {

  @NotBlank(message = "first name can't be empty")
  private String firstName;
  @NotBlank
  @JsonProperty("lastname")
  private String lastName;

  @NotBlank
  @JsonProperty("email")
  private String email;

  @NotBlank
  @JsonProperty("mobilePh")
  private String mobileNo;

  @JsonProperty("dob")
  private String dob;

  @NotBlank
  @JsonProperty("gender")
  private String gender;

  @NotBlank
  @JsonProperty("occupation")
  private String occupation;

  @JsonProperty("cuisine")
  private Cuisine cuisine; // enum

  @JsonProperty("foodcategory")
  private String foodCategory;

  @NotBlank
  @JsonProperty("address1")
  private String addressLine1;
  @JsonProperty("address2")
  private String addressLine2;
  @JsonProperty("latitude")
  private String latitude;
  @JsonProperty("longitude")
  private String longitude;
  @NotBlank
  @JsonProperty("city")
  private String city;

  @NotBlank
  @JsonProperty("istate")
  private String state;

  @NotBlank
  @JsonProperty("pin")
  private String pinCode;


}
