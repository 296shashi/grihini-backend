package com.project.grihini.core.onboarding.model;

import com.project.grihini.core.oauth.bo.UserInfoBO;
import com.project.grihini.core.oauth.entity.UserEntity;
import com.project.grihini.core.oauth.model.User;
import com.project.grihini.core.onboarding.entity.AddressEntity;
import com.project.grihini.core.onboarding.entity.BasicDetailsEntity;
import com.project.grihini.core.onboarding.entity.OnBoardingDataEntity;
import com.project.grihini.core.onboarding.model.enums.Cuisine;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@MappedSuperclass
public class MerchantInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String merchantId;
  private String username;
  private String password;
  private String oldpassword;
  private long basicDetailId;
  private long onboardingDataId;
  private long addressId;
  @Enumerated(EnumType.STRING)
  private Cuisine cuisine;
  private String merchantType;
  private String FoodCategory;
  @CreationTimestamp
  private Date created_at;
  @UpdateTimestamp
  private Date updated_at;

}
