package com.stc.mapper;

import com.stc.dom.UserPermission;
import com.stc.entity.PermissionGroupEntity;
import com.stc.entity.UserPermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPermissionMapper {

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "permissionGroup", expression = "java(permissionGroupEntity)")
    UserPermissionEntity toEntity(UserPermission userPermission, PermissionGroupEntity permissionGroupEntity);

    @Mapping(target = "id", expression = "java(userPermissionEntity.getId())")
    UserPermission fromEntity(UserPermissionEntity userPermissionEntity);
}
