package com.project.grihini.core.panel.model;

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
public class PanelUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long status;
  private Integer role;
  private Long merchantInfoId;
  private String username;
  private String currPassword;
  private String oldPassword;
  @CreationTimestamp
  private Date created_at;
  @UpdateTimestamp
  private Date updated_at;
}
