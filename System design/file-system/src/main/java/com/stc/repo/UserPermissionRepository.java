package com.stc.repo;

import com.stc.entity.PermissionGroupEntity;
import com.stc.entity.UserPermissionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserPermissionRepository extends CrudRepository<UserPermissionEntity, UUID> {

    Optional<UserPermissionEntity> findByEmailAndPermissionGroup(String email, PermissionGroupEntity permissionGroup);

    List<UserPermissionEntity> findByPermissionGroup(PermissionGroupEntity permissionGroupEntity);
}
