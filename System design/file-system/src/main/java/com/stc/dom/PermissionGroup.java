package com.stc.dom;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class PermissionGroup {

    private UUID id;

    @NotNull
    private String groupName;

    @NotNull
    private List<UserPermission> users;
}