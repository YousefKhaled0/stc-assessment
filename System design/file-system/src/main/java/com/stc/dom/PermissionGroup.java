package com.stc.dom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionGroup {

    private UUID id;

    @NotNull
    private String groupName;

    @Valid
    @NotNull
    private List<UserPermission> users;
}