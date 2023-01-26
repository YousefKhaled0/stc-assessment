package com.stc.repo;

import com.stc.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends CrudRepository<ItemEntity, UUID> {

    Optional<ItemEntity> findByNameAndType(String name, String type);
}
