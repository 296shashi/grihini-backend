package com.project.grihini.core.oauth.mapper;

import com.project.grihini.core.oauth.bo.AdminInfoBO;
import com.project.grihini.core.oauth.bo.UserInfoBO;
import com.project.grihini.core.oauth.entity.AdminEntity;
import com.project.grihini.core.oauth.entity.UserEntity;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminInfoBO entityToBo(final AdminEntity adminEntity);
    AdminEntity boToEntity(final AdminInfoBO adminInfoBO);
    List<AdminInfoBO> entityListToBo(final List<AdminEntity> adminEntities);
}
