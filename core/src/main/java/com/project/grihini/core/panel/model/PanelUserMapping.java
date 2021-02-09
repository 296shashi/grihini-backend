package com.project.grihini.core.panel.model;

import com.project.grihini.core.onboarding.model.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

@Data
@NoArgsConstructor
@ToString
@MappedSuperclass
public class PanelUserMapping {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long panelUserId;
  private Long addressId;
  private Long merchantInfoId;
  private Long onBoardingDataId;
  private Long role;

  @CreationTimestamp
  private Date created_at;
  @UpdateTimestamp
  private Date updated_at;
}
