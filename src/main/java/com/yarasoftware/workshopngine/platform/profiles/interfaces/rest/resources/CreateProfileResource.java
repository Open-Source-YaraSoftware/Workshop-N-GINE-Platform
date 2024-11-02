package com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.resources;

public record CreateProfileResource(String firstName, String lastName, int dni, String email, int age, String location, Long userId) {
}
