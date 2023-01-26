package com.stc.service;

import com.stc.dom.PermissionGroup;
import com.stc.dom.UserPermission;

public interface UserPermissionService {

    UserPermission addUserToGroup(PermissionGroup groupId, UserPermission userPermissions);
}
