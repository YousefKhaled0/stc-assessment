package com.stc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "items")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"type", "name", "parent_id"}))
public class ItemEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "parent")
    private List<ItemEntity> items;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ItemEntity parent;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroupEntity group;
}
