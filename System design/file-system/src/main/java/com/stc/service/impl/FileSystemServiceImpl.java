package com.stc.service.impl;

import com.stc.dom.*;
import com.stc.dom.error.FolderAlreadyExistsException;
import com.stc.dom.error.SpaceAlreadyExistsException;
import com.stc.dom.error.SpaceNotFoundException;
import com.stc.entity.ItemEntity;
import com.stc.entity.PermissionGroupEntity;
import com.stc.mapper.ItemMapper;
import com.stc.mapper.PermissionGroupMapper;
import com.stc.repo.ItemRepository;
import com.stc.service.AuthService;
import com.stc.service.FileSystemService;
import com.stc.service.PermissionGroupService;
import com.stc.service.UserPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileSystemServiceImpl implements FileSystemService {

    private final ItemRepository itemRepository;

    private final PermissionGroupService permissionGroupService;

    private final UserPermissionService userPermissionService;

    private final ItemMapper itemMapper;

    private final PermissionGroupMapper permissionGroupMapper;

    private final AuthService authService;

    @Override
    public Item createNewSpace(final Item item) {

        final String name = item.getName();

        final Optional<ItemEntity> itemOptional = itemRepository.findByNameAndType(name, ItemType.SPACE.name());

        if (itemOptional.isPresent()) {

            throw new SpaceAlreadyExistsException();
        }

        final PermissionGroup permissionGroup = permissionGroupService.createOrGetGroup(item.getGroup());

        final List<UserPermission> userPermissions = item.getGroup().getUsers().stream()
                .map(userPermission -> userPermissionService.addUserToGroup(permissionGroup, userPermission))
                .collect(Collectors.toList());

        permissionGroup.setUsers(userPermissions);

        final PermissionGroupEntity permissionGroupEntity = permissionGroupMapper.toEntity(permissionGroup);

        final ItemEntity itemEntity = itemMapper.toSpaceEntity(item, permissionGroupEntity);

        final ItemEntity savedItemEntity = itemRepository.save(itemEntity);

        return itemMapper.fromSpaceEntity(savedItemEntity, permissionGroup);
    }

    @Override
    public Item createNewFolder(FolderItem item, String spaceName, String user) {

        Optional<ItemEntity> spaceOptional = itemRepository.findByNameAndType(spaceName, ItemType.SPACE.name());

        if (!spaceOptional.isPresent()) {

            throw new SpaceNotFoundException();
        }

        final ItemEntity space = spaceOptional.get();

        final PermissionGroupEntity permissionGroupEntity = space.getGroup();

        final List<UserPermission> usersFromGroup = userPermissionService.getUsersFromGroup(permissionGroupEntity);

        final PermissionGroup permissionGroup = permissionGroupMapper.fromEntity(permissionGroupEntity);
        permissionGroup.setUsers(usersFromGroup);

        authService.authEditUser(user, permissionGroup);

        Optional<ItemEntity> folderOptional = itemRepository.findByNameAndTypeAndParent(item.getName(), ItemType.FOLDER.name(), space);

        if (folderOptional.isPresent()) {

            throw new FolderAlreadyExistsException();
        }

        final ItemEntity itemEntity = itemMapper.toFolderEntity(item, permissionGroupEntity, space);

        final ItemEntity savedItemEntity = itemRepository.save(itemEntity);

        final Item parent = itemMapper.fromSpaceEntity(space, permissionGroup);

        return itemMapper.fromFolderEntity(savedItemEntity, parent);
    }
}
