package com.project.grihini.core.onboarding.entity;

import com.project.grihini.core.onboarding.model.MerchantInfo;
import com.project.grihini.core.onboarding.model.enums.Cuisine;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "merchant_info")
public class MerchantInfoEntity extends MerchantInfo {

    @OneToOne(targetEntity = AddressEntity.class)
    @JoinColumn(name = "addressId", referencedColumnName = "id" ,insertable = false ,updatable = false)
    private AddressEntity address;

    @OneToOne(targetEntity = OnBoardingDataEntity.class)
    @JoinColumn(name = "onboardingDataId", referencedColumnName = "id" ,insertable = false ,updatable = false)
    private OnBoardingDataEntity onBoardingData;

    @OneToOne(targetEntity = BasicDetailsEntity.class)
    @JoinColumn(name = "basicDetailId", referencedColumnName = "id" ,insertable = false ,updatable = false)
    private BasicDetailsEntity basicDetails;
}
