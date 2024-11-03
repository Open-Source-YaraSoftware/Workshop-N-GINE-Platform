package com.yarasoftware.workshopngine.platform.service.domain.model.commands;

public record CreateClientCommand(String firstName, String lastName, Integer dni, String email, Integer age, String location, Long workshopId) {
    public CreateClientCommand {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("firstName must not be null or empty");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("lastName must not be null or empty");
        }
        if (dni == null || dni <= 0) {
            throw new IllegalArgumentException("dni must be greater than 0");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email must not be null or empty");
        }
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("age must be greater than 0");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("location must not be null or empty");
        }
        if (workshopId == null || workshopId <= 0) {
            throw new IllegalArgumentException("workshopId must be greater than 0");
        }
    }
}
