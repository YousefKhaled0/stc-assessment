package com.stc.service.impl;

import com.stc.dom.Permission;
import com.stc.dom.PermissionGroup;
import com.stc.dom.error.AuthException;
import com.stc.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public void authEditUser(String user, PermissionGroup permissionGroup) {

        boolean auth = permissionGroup.getUsers().stream()
                .anyMatch(userPermission -> userPermission.getEmail().equals(user) &&
                        userPermission.getPermission().equals(Permission.EDIT));
        if (!auth)
            throw new AuthException();
    }


    @Override
    public void authViewUser(String user, PermissionGroup permissionGroup) {

        boolean auth = permissionGroup.getUsers().stream()
                .anyMatch(userPermission -> userPermission.getEmail().equals(user) &&
                        (userPermission.getPermission().equals(Permission.VIEW) ||
                                userPermission.getPermission().equals(Permission.EDIT)));
        if (!auth)
            throw new AuthException();
    }
}
