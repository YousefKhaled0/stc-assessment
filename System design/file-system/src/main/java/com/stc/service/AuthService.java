package com.stc.service;

import com.stc.dom.PermissionGroup;
import com.stc.entity.ItemEntity;

public interface AuthService {

    PermissionGroup authEditUser(String user, ItemEntity space);

    PermissionGroup authViewUser(String user, ItemEntity space);
}
