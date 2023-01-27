package com.stc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "files_data")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    private byte[] content;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private ItemEntity item;
}
