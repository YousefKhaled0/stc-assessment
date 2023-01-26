package com.stc.mapper;

import com.stc.dom.Item;
import com.stc.dom.PermissionGroup;
import com.stc.entity.ItemEntity;
import com.stc.entity.PermissionGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "group", expression = "java(permissionGroupEntity)")
    @Mapping(target = "type", expression = "java(ItemType.SPACE.name())")
    ItemEntity toSpaceEntity(Item item, PermissionGroupEntity permissionGroupEntity);

    @Mapping(target = "id", expression = "java(itemEntity.getId())")
    @Mapping(target = "group", expression = "java(permissionGroup)")
    Item fromSpaceEntity(ItemEntity itemEntity, PermissionGroup permissionGroup);
}
