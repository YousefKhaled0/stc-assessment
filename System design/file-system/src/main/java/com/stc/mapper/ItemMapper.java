package com.stc.mapper;

import com.stc.dom.FileItem;
import com.stc.dom.FolderItem;
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
    Item fromEntity(ItemEntity itemEntity);

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "group", expression = "java(parent.getGroup())")
    @Mapping(target = "type", expression = "java(ItemType.FOLDER.name())")
    @Mapping(target = "name", expression = "java(item.getName())")
    @Mapping(target = "parent", expression = "java(parent)")
    @Mapping(target = "items", expression = "java(null)")
    ItemEntity toFolderEntity(FolderItem item, ItemEntity parent);


    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "group", expression = "java(parent.getGroup())")
    @Mapping(target = "type", expression = "java(ItemType.FILE.name())")
    @Mapping(target = "name", expression = "java(item.getName())")
    @Mapping(target = "parent", expression = "java(parent)")
    @Mapping(target = "items", expression = "java(null)")
    ItemEntity toFileEntity(FileItem item, ItemEntity parent);
}
