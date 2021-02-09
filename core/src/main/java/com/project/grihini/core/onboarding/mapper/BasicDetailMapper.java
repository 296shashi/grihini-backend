package com.project.grihini.core.onboarding.mapper;

import com.project.grihini.core.onboarding.bo.BasicInfoBo;
import com.project.grihini.core.onboarding.entity.BasicDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BasicDetailMapper {

  BasicDetailMapper INSTANCE = Mappers.getMapper(BasicDetailMapper.class);

  BasicInfoBo entityToBo(final BasicDetailsEntity basicDetailsEntity);

  BasicDetailsEntity boToEntity(final BasicInfoBo basicInfoBo);

  List<BasicInfoBo> entityListToBo(final List<BasicDetailsEntity> basicDetailsEntities);
}
