package com.stc.dom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileItem {

    private String name;

    private byte[] content;

    private FolderItem parent;
}
