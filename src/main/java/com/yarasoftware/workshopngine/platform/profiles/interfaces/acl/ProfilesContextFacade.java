package com.yarasoftware.workshopngine.platform.profiles.interfaces.acl;

public interface ProfilesContextFacade {

    Long createProfile(String firstName, String lastName, int dni, String email, int age, String location, Long userId);

    Long updateProfile(Long profileId, String firstName, String lastName, int dni, String email, int age, String location);
}
