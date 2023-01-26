package com.stc.mapper;

import com.stc.dom.FileItem;
import com.stc.entity.FileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {

    FileEntity toEntity(FileItem fileItem);

    FileItem fromEntity(FileEntity fileEntity);
}
