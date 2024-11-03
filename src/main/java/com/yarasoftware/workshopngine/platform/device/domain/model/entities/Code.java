package com.yarasoftware.workshopngine.platform.device.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yarasoftware.workshopngine.platform.device.domain.model.aggregates.IotDevice;
import com.yarasoftware.workshopngine.platform.device.domain.model.valueobjects.CodeState;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String component;
    private String errorCode;
    private String description;
    private Date lastUpdated;
    private CodeState state;

    @ManyToOne(optional = true)
    @JoinColumn(name = "iot_device_id")
    @JsonIgnore
    private IotDevice iotDevice;

    public Code() {
    }

    public Code(String component, String errorCode, String description, CodeState state) {
        this.component = component;
        this.errorCode = errorCode;
        this.description = description;
        this.lastUpdated = new Date(System.currentTimeMillis());
        this.state = state;
    }

}
