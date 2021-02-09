package com.project.grihini.core.onboarding.entity;

import com.project.grihini.core.onboarding.model.MenuInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "menu_info")
public class MenuInfoEntity extends MenuInfo {
}
