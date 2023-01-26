package com.stc.service.impl;

import com.stc.dom.Item;
import com.stc.dom.ItemType;
import com.stc.dom.PermissionGroup;
import com.stc.dom.UserPermission;
import com.stc.dom.error.SpaceAlreadyExistsException;
import com.stc.entity.ItemEntity;
import com.stc.entity.PermissionGroupEntity;
import com.stc.mapper.ItemMapper;
import com.stc.mapper.PermissionGroupMapper;
import com.stc.repo.ItemRepository;
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

    @Override
    public Item createNewSpace(final Item item) {

        final String name = item.getName();

        final Optional<ItemEntity> itemOptional = itemRepository.findByNameAndType(name, ItemType.SPACE.name());

        if (itemOptional.isPresent()) {

            throw new SpaceAlreadyExistsException();
        }

        final PermissionGroup group = permissionGroupService.createOrGetGroup(item.getGroup());

        final List<UserPermission> userPermissions = item.getGroup().getUsers().stream()
                .map(userPermission -> userPermissionService.addUserToGroup(group, userPermission))
                .collect(Collectors.toList());

        group.setUsers(userPermissions);

        final PermissionGroupEntity permissionGroupEntity = permissionGroupMapper.toEntity(group);

        final ItemEntity itemEntity = itemMapper.toSpaceEntity(item, permissionGroupEntity);

        final ItemEntity savedItemEntity = itemRepository.save(itemEntity);

        return itemMapper.fromSpaceEntity(savedItemEntity , group);
    }
}
