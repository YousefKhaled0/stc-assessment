package com.stc.controller;

import com.stc.dom.FileItem;
import com.stc.dom.FolderItem;
import com.stc.dom.Item;
import com.stc.service.FileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FileSystemController {

    private final FileSystemService fileSystemService;

    @PostMapping("/space")
    public Item createNewSpace(@RequestBody @Valid final Item item) {

        return fileSystemService.createNewSpace(item);
    }

    @PostMapping("/parent/{parentId}/folder")
    public Item createNewSpace(@RequestBody @Valid final FolderItem folderItem,
                               @PathVariable final UUID parentId,
                               @RequestHeader(name = "user", required = false) final String user
    ) {

        return fileSystemService.createNewFolder(folderItem, parentId, user);
    }


    @PostMapping("/parent/{parentId}/file")
    public Item createNewFile(@RequestParam("file") final MultipartFile file,
                              @PathVariable final UUID parentId,
                              @RequestHeader(name = "user", required = false) final String user
    ) throws IOException {

        final FileItem fileItem = FileItem.builder()
                .name(file.getOriginalFilename())
                .content(file.getBytes()).build();

        return fileSystemService.createNewFile(fileItem, parentId, user);
    }

    @GetMapping("/file/{fileId}")
    public Item getFileMetaData(@PathVariable final UUID fileId,
                                @RequestHeader(name = "user", required = false) final String user
    ) {


        return fileSystemService.getFileMetaData(fileId , user);
    }

    @GetMapping("/file/{fileId}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable final UUID fileId,
                                                          @RequestHeader(name = "user", required = false) final String user
    ) {


        final byte[] content = fileSystemService.downloadFile(fileId, user);

        final ByteArrayResource resource = new ByteArrayResource(content);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
