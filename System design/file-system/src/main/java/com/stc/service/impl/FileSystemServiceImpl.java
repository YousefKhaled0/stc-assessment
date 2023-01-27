package com.stc.service.impl;

import com.stc.advice.error.ItemAlreadyExistsException;
import com.stc.advice.error.ItemNotFoundException;
import com.stc.dom.*;
import com.stc.entity.FileEntity;
import com.stc.entity.ItemEntity;
import com.stc.entity.PermissionGroupEntity;
import com.stc.mapper.FileMapper;
import com.stc.mapper.ItemMapper;
import com.stc.mapper.PermissionGroupMapper;
import com.stc.repo.FileRepo;
import com.stc.repo.ItemRepository;
import com.stc.service.AuthService;
import com.stc.service.FileSystemService;
import com.stc.service.PermissionGroupService;
import com.stc.service.UserPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileSystemServiceImpl implements FileSystemService {

    private final ItemRepository itemRepository;

    private final PermissionGroupService permissionGroupService;

    private final UserPermissionService userPermissionService;

    private final ItemMapper itemMapper;

    private final PermissionGroupMapper permissionGroupMapper;

    private final FileRepo fileRepo;

    private final FileMapper fileMapper;

    private final AuthService authService;

    @Override
    public Item createNewSpace(final Item item) {

        checkItemAlreadyExists(item.getName(), ItemType.SPACE, null);

        final PermissionGroup permissionGroup = permissionGroupService.createOrGetGroup(item.getGroup());

        item.getGroup().getUsers()
                .forEach(userPermission -> userPermissionService.addUserToGroup(permissionGroup, userPermission));

        final PermissionGroupEntity permissionGroupEntity = permissionGroupMapper.toEntity(permissionGroup);

        final ItemEntity itemEntity = itemMapper.toSpaceEntity(item, permissionGroupEntity);

        final ItemEntity savedItemEntity = itemRepository.save(itemEntity);

        return itemMapper.fromEntity(savedItemEntity);
    }

    @Override
    public Item createNewFolder(FolderItem item, UUID parentId, String user) {

        final ItemEntity parentItem = getParentItem(parentId);

        authService.authEditUser(user, parentItem);

        checkItemAlreadyExists(item.getName(), ItemType.FOLDER, parentItem);

        final ItemEntity itemEntity = itemMapper.toFolderEntity(item, parentItem);

        final ItemEntity savedItemEntity = itemRepository.save(itemEntity);

        return itemMapper.fromEntity(savedItemEntity);
    }

    @Override
    public Item createNewFile(final FileItem fileItem, final UUID parentId, final String user) {

        final ItemEntity parentItem = getParentItem(parentId);

        authService.authEditUser(user, parentItem);

        final ItemEntity fileMetaData = itemMapper.toFileEntity(fileItem, parentItem);

        checkItemAlreadyExists(fileItem.getName(), ItemType.FILE, parentItem);

        final ItemEntity savedFileMetaData = itemRepository.save(fileMetaData);

        final FileEntity fileEntity = fileMapper.toEntity(fileItem);

        fileRepo.save(fileEntity);

        return itemMapper.fromEntity(savedFileMetaData);
    }

    private void checkItemAlreadyExists(final String name, final ItemType type, final ItemEntity parent) {

        final Optional<ItemEntity> folderOptional = itemRepository.findByNameAndTypeAndParent(name, type.name(), parent);

        if (folderOptional.isPresent()) {

            throw new ItemAlreadyExistsException();
        }
    }

    private ItemEntity getParentItem(final UUID parentId) {

        final Optional<ItemEntity> itemEntityOptional = itemRepository.findById(parentId);

        if (!itemEntityOptional.isPresent()) {

            throw new ItemNotFoundException();
        }

        final ItemEntity itemEntity = itemEntityOptional.get();

        if (!Objects.equals(itemEntity.getType(), ItemType.FOLDER.name()) &&
                !Objects.equals(itemEntity.getType(), ItemType.SPACE.name())) {

            throw new ItemNotFoundException();
        }

        return itemEntity;
    }
}
