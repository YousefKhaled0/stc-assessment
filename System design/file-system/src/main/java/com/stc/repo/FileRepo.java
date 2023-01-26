package com.stc.repo;

import com.stc.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FileRepo extends CrudRepository<FileEntity, UUID> {
}
