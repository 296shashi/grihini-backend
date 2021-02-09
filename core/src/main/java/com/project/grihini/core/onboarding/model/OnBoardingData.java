package com.project.grihini.core.onboarding.model;

import com.project.grihini.core.onboarding.enums.OnboardingStatus;
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
public class OnBoardingData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private long panelId;
  private String request;
  private String error;
  private Long errorCode;
  @Enumerated(EnumType.STRING)
  private OnboardingStatus status;
  @CreationTimestamp
  private Date created_at;
  @UpdateTimestamp
  private Date updated_at;
}
