package com.project.grihini.core.oauth.mapper;

import com.project.grihini.core.oauth.bo.UserInfoBO;
import com.project.grihini.core.oauth.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserInfoBO entityToBo(final UserEntity userEntity);
    UserEntity boToEntity(final UserInfoBO userInfoBO);
    List<UserInfoBO> entityListToBo(final List<UserEntity> userEntities);
}
