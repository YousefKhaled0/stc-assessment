package com.stc.service.impl;

import com.stc.advice.error.FolderAlreadyExistsException;
import com.stc.advice.error.SpaceNotFoundException;
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

        final ItemEntity spaceEntity = getSpace(spaceName);

        final PermissionGroupEntity permissionGroupEntity = spaceEntity.getGroup();

        final List<UserPermission> usersFromGroup = userPermissionService.getUsersFromGroup(permissionGroupEntity);

        final PermissionGroup permissionGroup = permissionGroupMapper.fromEntity(permissionGroupEntity);
        permissionGroup.setUsers(usersFromGroup);

        authService.authEditUser(user, permissionGroup);

        checkItemAlreadyExists(item.getName(), ItemType.FOLDER, spaceEntity);

        final ItemEntity itemEntity = itemMapper.toFolderEntity(item, permissionGroupEntity, spaceEntity);

        final ItemEntity savedItemEntity = itemRepository.save(itemEntity);

        final Item parent = itemMapper.fromSpaceEntity(spaceEntity, permissionGroup);

        return itemMapper.fromFolderEntity(savedItemEntity, parent);
    }

    @Override
    public Item createNewFile(FileItem fileItem, String spaceName, String folderName, String user) {

        return null;
    }

    private void checkItemAlreadyExists(final String name, final ItemType type, final ItemEntity parent) {

        Optional<ItemEntity> folderOptional = itemRepository.findByNameAndTypeAndParent(name, type.name(), parent);

        if (folderOptional.isPresent()) {

            throw new FolderAlreadyExistsException();
        }
    }

    private ItemEntity getSpace(final String spaceName) {

        Optional<ItemEntity> spaceOptional = itemRepository.findByNameAndType(spaceName, ItemType.SPACE.name());

        if (!spaceOptional.isPresent()) {

            throw new SpaceNotFoundException();
        }

        return spaceOptional.get();
    }
}
