package com.project.grihini.core.onboarding.repo;

import com.project.grihini.core.onboarding.entity.MenuInfoEntity;
import com.project.grihini.core.onboarding.entity.MerchantInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuInfoRepo extends JpaRepository<MenuInfoEntity, Long> {
   // MenuInfoEntity findByMerchant_info_id(Long id);

}
