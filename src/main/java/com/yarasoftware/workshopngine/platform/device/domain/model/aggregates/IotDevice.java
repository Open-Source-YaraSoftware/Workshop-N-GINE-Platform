package com.yarasoftware.workshopngine.platform.device.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yarasoftware.workshopngine.platform.device.domain.model.entities.Code;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class IotDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId;

    @OneToMany(mappedBy = "iotDevice")
    @JsonManagedReference
    private List<Code> codeList;

    public IotDevice() {
    }

    public IotDevice(List<Code> codeList, Long vehicleId) {
        this.vehicleId = vehicleId;
        this.codeList = codeList;
    }

}