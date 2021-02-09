package com.project.grihini.core.onboarding.mapper;

import com.project.grihini.core.onboarding.bo.OnBoardingDataBO;
import com.project.grihini.core.onboarding.entity.OnBoardingDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OnBoardingDataMapper {

  OnBoardingDataMapper INSTANCE = Mappers.getMapper(OnBoardingDataMapper.class);

  OnBoardingDataBO entityToBo(final OnBoardingDataEntity onBoardingDataEntity);

  OnBoardingDataEntity boToEntity(final OnBoardingDataBO onBoardingDataBO);

  List<OnBoardingDataBO> entityListToBo(final List<OnBoardingDataEntity> onBoardingDataEntities);
}
