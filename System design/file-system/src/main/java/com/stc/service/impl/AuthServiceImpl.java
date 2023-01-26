package com.stc.service.impl;

import com.stc.advice.error.AuthException;
import com.stc.dom.Permission;
import com.stc.dom.PermissionGroup;
import com.stc.dom.UserPermission;
import com.stc.entity.ItemEntity;
import com.stc.entity.PermissionGroupEntity;
import com.stc.mapper.PermissionGroupMapper;
import com.stc.service.AuthService;
import com.stc.service.UserPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserPermissionService userPermissionService;

    private final PermissionGroupMapper permissionGroupMapper;

    @Override
    public PermissionGroup authEditUser(final String user, final ItemEntity space) {

        final PermissionGroupEntity permissionGroupEntity = space.getGroup();

        final PermissionGroup permissionGroup = permissionGroupMapper.fromEntity(permissionGroupEntity);

        final List<UserPermission> usersFromGroup = userPermissionService.getUsersFromGroup(permissionGroup);
        permissionGroup.setUsers(usersFromGroup);

        final boolean auth = permissionGroup.getUsers()
                .stream()
                .anyMatch(userPermission -> userPermission.getEmail().equals(user) &&
                        userPermission.getPermission().equals(Permission.EDIT));

        if (!auth)
            throw new AuthException();

        return permissionGroup;
    }

    @Override
    public PermissionGroup authViewUser(final String user, final ItemEntity space) {

        final PermissionGroupEntity permissionGroupEntity = space.getGroup();

        final PermissionGroup permissionGroup = permissionGroupMapper.fromEntity(permissionGroupEntity);

        final List<UserPermission> usersFromGroup = userPermissionService.getUsersFromGroup(permissionGroup);
        permissionGroup.setUsers(usersFromGroup);

        final boolean auth = permissionGroup.getUsers()
                .stream()
                .anyMatch(userPermission ->
                        userPermission.getEmail().equals(user) &&
                                (userPermission.getPermission().equals(Permission.EDIT) ||
                                        userPermission.getPermission().equals(Permission.VIEW))
                );

        if (!auth)
            throw new AuthException();

        return permissionGroup;
    }
}
