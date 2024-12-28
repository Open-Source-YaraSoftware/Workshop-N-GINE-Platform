package com.yarasoftware.workshopngine.platform.file.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.file.domain.model.commands.CreateFileCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Setter
@Entity
public class File extends AbstractAggregateRoot<File> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Long size;

    @Lob
    private byte[] data;

    public File() {
    }

    public File(CreateFileCommand command){
        this.name = command.name();
        this.type = command.type();
        this.size = command.size();
        this.data = command.data();
    }
}
