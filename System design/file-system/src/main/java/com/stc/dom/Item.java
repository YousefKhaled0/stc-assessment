package com.stc.dom;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class Item {

    private UUID id;

    @NotNull
    private String name;

    private ItemType type;

    @NotNull
    private PermissionGroup group;
}