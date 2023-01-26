package com.stc.controller;

import com.stc.dom.Item;
import com.stc.service.FileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class FileSystemController {

    private final FileSystemService fileSystemService;

    @PostMapping("/space")
    public Item createNewSpace(@RequestBody @Valid final Item item) {

        return fileSystemService.createNewSpace(item);
    }
}
