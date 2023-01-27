package com.stc.service.impl;

import com.stc.dom.PermissionGroup;
import com.stc.dom.UserPermission;
import com.stc.advice.error.PermissionGroupNotFoundException;
import com.stc.entity.PermissionGroupEntity;
import com.stc.entity.UserPermissionEntity;
import com.stc.mapper.UserPermissionMapper;
import com.stc.repo.PermissionGroupRepository;
import com.stc.repo.UserPermissionRepository;
import com.stc.service.UserPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPermissionServiceImpl implements UserPermissionService {

    private final PermissionGroupRepository permissionGroupRepository;

    private final UserPermissionRepository userPermissionRepository;

    private final UserPermissionMapper userPermissionMapper;

    @Override
    public UserPermission addUserToGroup(final PermissionGroup permissionGroup, final UserPermission userPermission) {

        final PermissionGroupEntity permissionGroupEntity = permissionGroupRepository.findById(permissionGroup.getId())
                .orElseThrow(PermissionGroupNotFoundException::new);

        final String email = userPermission.getEmail();

        final Optional<UserPermissionEntity> userPermissionOptional = userPermissionRepository.findByEmailAndPermissionGroup(email, permissionGroupEntity);

        if (userPermissionOptional.isPresent()) {

            final UserPermissionEntity userPermissionEntity = userPermissionOptional.get();

            return userPermissionMapper.fromEntity(userPermissionEntity);
        }

        final UserPermissionEntity userPermissionEntity = userPermissionMapper.toEntity(userPermission, permissionGroupEntity);

        final UserPermissionEntity savedUserPermissionEntity = userPermissionRepository.save(userPermissionEntity);

        return userPermissionMapper.fromEntity(savedUserPermissionEntity);
    }

    @Override
    public List<UserPermission> getUsersFromGroup(final PermissionGroup permissionGroup) {

        final PermissionGroupEntity permissionGroupEntity = permissionGroupRepository.findById(permissionGroup.getId())
                .orElseThrow(PermissionGroupNotFoundException::new);

        final List<UserPermissionEntity> userPermissionEntities = userPermissionRepository.findByPermissionGroup(permissionGroupEntity);

        return userPermissionEntities
                .stream()
                .map(userPermissionMapper::fromEntity)
                .collect(Collectors.toList());
    }
}
