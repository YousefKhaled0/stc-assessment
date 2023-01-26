package com.stc.dom;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserPermission {

    private UUID id;

    @Email
    @NotNull
    private String email;

    @NotNull
    private Permission permission;
}
