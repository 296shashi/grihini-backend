package com.project.grihini.core.onboarding.repo;

import com.project.grihini.core.onboarding.entity.OnBoardingDataEntity;
import com.project.grihini.core.onboarding.enums.OnboardingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnBoardingDataRepo extends JpaRepository<OnBoardingDataEntity, Long> {

    @Query("select o.id from OnBoardingDataEntity o where o.status in (?1)")
    List<Long> findIdByStatus(List<OnboardingStatus> status);

    @Query("select o.status from OnBoardingDataEntity o where o.id=?1 ")
    String findStatusById(Long id);
}
