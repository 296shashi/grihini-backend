package com.project.grihini.core.onboarding.repo;

import com.project.grihini.core.onboarding.entity.MerchantInfoEntity;
import com.project.grihini.core.onboarding.model.BasicDetails;
import com.project.grihini.core.onboarding.model.MerchantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantInfoRepo extends JpaRepository<MerchantInfoEntity, Long> {

  Optional<MerchantInfoEntity> findByMerchantId(String merchantId);

  Optional<MerchantInfoEntity> findByUsernameAndPassword(String username,String password);


//  @Query("select u.userName from User u inner join u.area ar where ar.idArea = :idArea")

//  @Query("select m_info.merchantId from 'merchant_info' m_info inner join m_info.basicDetailId b_id where b_id.status = :status")
//  public List<MerchantInfoEntity> findByStatus(@Param("status") String status);

//  @Query("select mi.merchantId from MerchantInfoEntity mi left join BasicDetailsEntity bd on mi.id= bd.id where bd.status =:status")
//  public List<MerchantInfoEntity> findByStatus(@Param("status") String status);
//  @Query("SELECT u FROM 'merchant_info' m_info WHERE LOWER(p.lastName) = LOWER(:lastName)")
//  public List<MerchantInfoEntity> findByStatus(@Param("status") String status);



  @Query("SELECT m from MerchantInfoEntity m join m.address a on m.addressId=a.id join m.basicDetails b on m.basicDetailId=b.id where m.onboardingDataId in (?1)")
  List<MerchantInfoEntity> findAllByBasicDetailIdIsIn(List<Long> onboardingDataId);

}
