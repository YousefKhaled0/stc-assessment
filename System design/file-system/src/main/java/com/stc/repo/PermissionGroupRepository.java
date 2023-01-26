package com.stc.repo;

import com.stc.entity.PermissionGroupEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PermissionGroupRepository extends CrudRepository<PermissionGroupEntity, UUID> {

    Optional<PermissionGroupEntity> findByGroupName(String name);
}
