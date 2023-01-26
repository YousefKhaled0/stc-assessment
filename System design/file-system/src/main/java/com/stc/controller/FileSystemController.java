package com.stc.controller;

import com.stc.dom.FileItem;
import com.stc.dom.FolderItem;
import com.stc.dom.Item;
import com.stc.service.FileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileSystemController {

    private final FileSystemService fileSystemService;

    @PostMapping("/space")
    public Item createNewSpace(@RequestBody @Valid final Item item) {

        return fileSystemService.createNewSpace(item);
    }

    @PostMapping("/space/{name}/folder")
    public Item createNewSpace(@RequestBody @Valid final FolderItem folderItem,
                               @PathVariable String name,
                               @RequestHeader(name = "user", required = false) String user
    ) {

        return fileSystemService.createNewFolder(folderItem, name, user);
    }

    @PostMapping("/space/{spaceName}/folder/{folderName}")
    public Item createNewFile(@RequestParam("file") MultipartFile file,
                              @PathVariable String spaceName,
                              @PathVariable String folderName,
                              @RequestHeader(name = "user", required = false) String user
    ) throws IOException {

        final FileItem fileItem = FileItem.builder()
                .name(file.getName())
                .content(file.getBytes()).build();

        return fileSystemService.createNewFile(fileItem, spaceName, folderName, user);
    }
}
