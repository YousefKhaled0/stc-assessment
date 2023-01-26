package com.stc.mapper;

import com.stc.dom.FolderItem;
import com.stc.dom.Item;
import com.stc.dom.PermissionGroup;
import com.stc.entity.FileEntity;
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

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "group", expression = "java(parent.getGroup())")
    @Mapping(target = "type", expression = "java(ItemType.FOLDER.name())")
    @Mapping(target = "name", expression = "java(item.getName())")
    @Mapping(target = "parent", expression = "java(parent)")
    @Mapping(target = "items", expression = "java(null)")
    ItemEntity toFolderEntity(FolderItem item, ItemEntity parent);

    @Mapping(target = "id", expression = "java(itemEntity.getId())")
    @Mapping(target = "name", expression = "java(itemEntity.getName())")
    @Mapping(target = "group", expression = "java(null)")
    @Mapping(target = "type", expression = "java(ItemType.FOLDER)")
    @Mapping(target = "parent", expression = "java(parent)")
    Item fromFolderEntity(ItemEntity itemEntity, Item parent);
}
