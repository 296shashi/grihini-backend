package com.project.grihini.core.onboarding.model;

import com.project.grihini.core.onboarding.enums.OnboardingStatus;
import com.project.grihini.core.onboarding.model.enums.PanelUserStatus;
import lombok.Builder;
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
public class BasicDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String firstName;
  private String lastName;
  private String mobileNo;
  private String email;
  private String gender;
  private String dob;
  private String imgUrl;
  private String documentUrl;
  @Enumerated(EnumType.STRING)
  private PanelUserStatus status;
  @CreationTimestamp
  private Date created_at;
  @UpdateTimestamp
  private Date updated_at;
}
