package com.yarasoftware.workshopngine.platform.service.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Setter
@Entity
public class Workshop extends AbstractAggregateRoot<Workshop> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    // TODO: Add intervention list field

    public Workshop() {
        this.name = Strings.EMPTY;
    }

    public Workshop(String name) {
        this();
        this.name = name;
    }
}
