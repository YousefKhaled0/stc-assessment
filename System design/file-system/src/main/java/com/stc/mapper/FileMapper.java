package com.stc.mapper;

import com.stc.dom.FileItem;
import com.stc.entity.FileEntity;
import com.stc.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "item", expression = "java(savedFileMetaData)")
    FileEntity toEntity(FileItem fileItem, ItemEntity savedFileMetaData);

    FileItem fromEntity(FileEntity fileEntity);
}
