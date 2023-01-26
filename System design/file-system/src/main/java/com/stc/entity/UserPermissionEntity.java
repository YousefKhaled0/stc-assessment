package com.stc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "user_permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPermissionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    private String email;

    private String permission;

    @OneToOne
    @JoinColumn(name = "groupId", referencedColumnName = "id")
    private PermissionGroupEntity permissionGroup;
}
