package com.project.grihini.core.onboarding.repo;

import com.project.grihini.core.onboarding.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, Long> {

  List<AddressEntity> findAllByPinCodeInOrderByStateDesc(List<String> pincodes);
  Optional<AddressEntity> findById(Long id);
}
