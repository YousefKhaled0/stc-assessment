package com.stc.repo;

import com.stc.entity.FileEntity;
import com.stc.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface FileRepo extends CrudRepository<FileEntity, UUID> {

    Optional<FileEntity> findByItem(ItemEntity item);
}
