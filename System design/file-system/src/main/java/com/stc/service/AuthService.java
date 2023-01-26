package com.stc.service;

import com.stc.dom.PermissionGroup;

public interface AuthService {

    void authEditUser(String user, PermissionGroup permissionGroup);

    void authViewUser(String user, PermissionGroup permissionGroup);
}
