package com.stc.service;

import com.stc.dom.PermissionGroup;

public interface PermissionGroupService {

    PermissionGroup createOrGetGroup(final PermissionGroup group);
}
