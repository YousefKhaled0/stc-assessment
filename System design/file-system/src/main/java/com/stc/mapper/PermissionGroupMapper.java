package com.stc.mapper;

import com.stc.dom.PermissionGroup;
import com.stc.entity.PermissionGroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionGroupMapper {

    PermissionGroupEntity toEntity(PermissionGroup permissionGroup);

    PermissionGroup fromEntity(PermissionGroupEntity permissionGroupEntity);
}
