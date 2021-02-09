package com.project.grihini.core.onboarding.mapper;

import com.project.grihini.core.onboarding.bo.MerchantInfoBO;
import com.project.grihini.core.onboarding.entity.MerchantInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MerchantInfoMapper {

  MerchantInfoMapper INSTANCE = Mappers.getMapper(MerchantInfoMapper.class);

  MerchantInfoBO entityToBo(final MerchantInfoEntity merchantInfoEntity);

  MerchantInfoEntity boToEntity(final MerchantInfoBO merchantInfoBO);

  List<MerchantInfoBO> entityListToBo(final List<MerchantInfoEntity> merchantInfoEntityList);
}
