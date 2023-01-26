package com.stc.service;

import com.stc.dom.PermissionGroup;
import com.stc.dom.UserPermission;
import com.stc.entity.PermissionGroupEntity;

import java.util.List;

public interface UserPermissionService {

    UserPermission addUserToGroup(PermissionGroup groupId, UserPermission userPermissions);

    List<UserPermission> getUsersFromGroup(PermissionGroup permissionGroup);
}
