package com.project.grihini.core.onboarding.repo;

import com.project.grihini.core.onboarding.entity.BasicDetailsEntity;
import com.project.grihini.core.onboarding.model.BasicDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasicInfoRepo extends JpaRepository<BasicDetailsEntity, Long> {

    Optional<BasicDetailsEntity> findById(Long id);
    List<BasicDetailsEntity> findBasicDetailsByEmailAndMobileNo(String email, String mobileNo);
    @Query("select b.status from BasicDetailsEntity b where b.id=?1")
    String findStatusById(Long id);

    @Query("select CONCAT(b.firstName,' ' , b.lastName) as fullname from BasicDetailsEntity b where b.id=?1")
    String findFullNameById(Long id);
}
