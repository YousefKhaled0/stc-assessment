package com.stc.dom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private UUID id;

    @NotNull
    private String name;

    private ItemType type;

    @NotNull
    @Valid
    private PermissionGroup group;

    private Item parent;
}