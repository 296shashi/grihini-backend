package com.project.grihini.core.onboarding.mapper;

import com.project.grihini.core.onboarding.bo.AddressBO;
import com.project.grihini.core.onboarding.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {

  AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

  AddressBO entityToBo(final AddressEntity addressEntity);
  AddressEntity boToEntity(final AddressBO addressBO);
  List<AddressBO> entityListToBo(final List<AddressEntity> addressEntities);
}
