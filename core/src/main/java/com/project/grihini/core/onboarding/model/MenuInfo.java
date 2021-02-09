package com.project.grihini.core.onboarding.model;

import com.project.grihini.core.onboarding.model.enums.MenuStatus;
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
public class MenuInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private long merchant_info_id;
  private String items;
  private double price;
  private String type;
  @Enumerated(EnumType.STRING)
  private MenuStatus status;
  private String cuisine;
  private String imgUrl;
  @CreationTimestamp
  private Date created_at;
  @UpdateTimestamp
  private Date updated_at;
}
