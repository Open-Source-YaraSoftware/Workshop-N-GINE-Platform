package com.yarasoftware.workshopngine.platform.service.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

/**
 * Represents a vehicle.
 * The vehicle is an aggregate root
 */
@Getter
@Setter
@Entity
public class Vehicle extends AbstractAggregateRoot<Vehicle> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String brand;
    private String model;
    private Date registrationDate;
    private Long clientId;
    private Long iotDeviceId;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, String brand, String model, Long clientId, Long iotDeviceId) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.registrationDate = new Date(System.currentTimeMillis());
        this.clientId = clientId;
        this.iotDeviceId = iotDeviceId;
    }

    /**
     * Returns the full name of the vehicle
     */
    public String getFullName() {
        return this.brand + " " + this.model;
    }


}
