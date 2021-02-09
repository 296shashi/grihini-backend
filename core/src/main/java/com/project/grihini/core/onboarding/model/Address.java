package com.project.grihini.core.onboarding.model;

import com.project.grihini.core.onboarding.model.enums.AddressStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@MappedSuperclass
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String pinCode;
  private String latitude;
  private String longitude;
  @Enumerated(EnumType.STRING)
  private AddressStatus status;
  @CreationTimestamp
  private Date created_at;
  @UpdateTimestamp
  private Date updated_at;

}
