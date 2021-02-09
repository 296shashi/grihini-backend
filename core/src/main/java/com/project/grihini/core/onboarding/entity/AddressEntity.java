package com.project.grihini.core.onboarding.entity;

import com.project.grihini.core.onboarding.model.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "address")
public class AddressEntity extends Address {
}
