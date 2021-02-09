package com.project.grihini.core.oauth.repo;

import com.project.grihini.core.oauth.entity.AdminEntity;
import com.project.grihini.core.onboarding.entity.BasicDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepo extends JpaRepository<AdminEntity,Long> {

    Optional<AdminEntity> findById(Long id);
    List<AdminEntity> findAdminEntitiesByEmailAndPassword(String username, String password);
    List<AdminEntity> findAdminEntitiesByUsernameAndPassword(String username,String password);
    List<AdminEntity> findAdminEntityByUsernameAndPassword(String username,String password);

}
