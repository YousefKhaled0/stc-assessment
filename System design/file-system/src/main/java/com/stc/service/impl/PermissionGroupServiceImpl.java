package com.stc.service.impl;

import com.stc.dom.PermissionGroup;
import com.stc.entity.PermissionGroupEntity;
import com.stc.mapper.PermissionGroupMapper;
import com.stc.repo.PermissionGroupRepository;
import com.stc.service.PermissionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionGroupServiceImpl implements PermissionGroupService {

    private final PermissionGroupRepository permissionGroupRepository;

    private final PermissionGroupMapper permissionGroupMapper;

    @Override
    public PermissionGroup createOrGetGroup(final PermissionGroup group) {

        final String groupName = group.getGroupName();

        final Optional<PermissionGroupEntity> groupOptional = permissionGroupRepository.findByGroupName(groupName);

        if (groupOptional.isPresent()) {

            final PermissionGroupEntity permissionGroupEntity = groupOptional.get();

            return permissionGroupMapper.fromEntity(permissionGroupEntity);
        }

        final PermissionGroupEntity permissionGroupEntity = permissionGroupMapper.toEntity(group);

        final PermissionGroupEntity savedPermissionGroup = permissionGroupRepository.save(permissionGroupEntity);

        return permissionGroupMapper.fromEntity(savedPermissionGroup);
    }
}
