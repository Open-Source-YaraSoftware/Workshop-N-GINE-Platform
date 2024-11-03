package com.yarasoftware.workshopngine.platform.service.interfaces.rest.resources;

public record CreateClientResource(String firstName, String lastName, Integer dni, String email, Integer age, String location) {
    public CreateClientResource {
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
    }
}
