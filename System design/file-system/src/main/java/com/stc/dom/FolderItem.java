package com.stc.dom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderItem {

    @NotNull
    private String name;
}

