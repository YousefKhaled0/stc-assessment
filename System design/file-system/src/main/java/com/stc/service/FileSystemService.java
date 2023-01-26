package com.stc.service;

import com.stc.dom.FolderItem;
import com.stc.dom.Item;

public interface FileSystemService {

    Item createNewSpace(Item item);

    Item createNewFolder(FolderItem item, String spaceName, String user);
}
