package com.stc.controller;

import com.stc.dom.FolderItem;
import com.stc.dom.Item;
import com.stc.service.FileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                               @RequestHeader(name = "user", required = false) @Valid String user
    ) {

        return fileSystemService.createNewFolder(folderItem, name, user);
    }
}
