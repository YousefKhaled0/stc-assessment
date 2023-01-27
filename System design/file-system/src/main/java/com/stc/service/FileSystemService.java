package com.stc.service;

import com.stc.dom.FileItem;
import com.stc.dom.FolderItem;
import com.stc.dom.Item;

import java.util.UUID;

public interface FileSystemService {

    Item createNewSpace(Item item);

    Item createNewFolder(FolderItem item, UUID parentId, String user);

    Item createNewFile(FileItem fileItem, UUID parentId, String user);

    byte[] downloadFile(UUID fileId);
}
