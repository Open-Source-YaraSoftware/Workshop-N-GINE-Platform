package com.yarasoftware.workshopngine.platform.iam.infrastructure.authorization.sfs.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class UserAccountInfo {
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private String provider;
    private String providerId;
    private String nickname;
    private String phoneNumber;
    private LocalDate birthdate;
    private Map<String, Object> attributes;
}