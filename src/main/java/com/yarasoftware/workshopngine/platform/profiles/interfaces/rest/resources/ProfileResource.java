package com.yarasoftware.workshopngine.platform.profiles.interfaces.rest.resources;

public record ProfileResource(long id, String firstName, String lastName, int dni, String email, int age, String location, long userId) {
}