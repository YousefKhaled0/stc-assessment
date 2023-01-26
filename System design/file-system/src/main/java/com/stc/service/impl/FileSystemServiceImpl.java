package com.stc.service.impl;

import com.stc.advice.error.ItemAlreadyExistsException;
import com.stc.advice.error.ItemNotFoundException;
import com.stc.dom.*;
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

        checkItemAlreadyExists(item.getName(), ItemType.SPACE, null);

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

        final ItemEntity spaceEntity = getItem(spaceName, ItemType.SPACE, null);

        final PermissionGroup permissionGroup = authService.authEditUser(user, spaceEntity);

        checkItemAlreadyExists(item.getName(), ItemType.FOLDER, spaceEntity);

        final ItemEntity itemEntity = itemMapper.toFolderEntity(item, spaceEntity);

        final ItemEntity savedItemEntity = itemRepository.save(itemEntity);

        final Item parent = itemMapper.fromSpaceEntity(spaceEntity, permissionGroup);

        return itemMapper.fromFolderEntity(savedItemEntity, parent);
    }

    @Override
    public Item createNewFile(final FileItem fileItem, final String spaceName, final String folderName, final String user) {

        final ItemEntity spaceEntity = getItem(spaceName, ItemType.SPACE, null);

        final PermissionGroup permissionGroup = authService.authEditUser(user, spaceEntity);

        final ItemEntity folderEntity = getItem(spaceName, ItemType.FOLDER, spaceEntity);

        return null;
    }

    private void checkItemAlreadyExists(final String name, final ItemType type, final ItemEntity parent) {

        final Optional<ItemEntity> folderOptional = itemRepository.findByNameAndTypeAndParent(name, type.name(), parent);

        if (folderOptional.isPresent()) {

            throw new ItemAlreadyExistsException();
        }
    }

    private ItemEntity getItem(final String name, final ItemType type, final ItemEntity parent) {

        final Optional<ItemEntity> itemEntityOptional = itemRepository.findByNameAndTypeAndParent(name, type.name(), parent);

        if (!itemEntityOptional.isPresent()) {

            throw new ItemNotFoundException();
        }

        return itemEntityOptional.get();
    }
}
