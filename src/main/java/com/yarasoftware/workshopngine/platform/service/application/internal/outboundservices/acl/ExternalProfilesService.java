package com.yarasoftware.workshopngine.platform.service.application.internal.outboundservices.acl;

import com.yarasoftware.workshopngine.platform.profiles.interfaces.acl.ProfilesContextFacade;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.ProfileId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfilesService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfilesService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<ProfileId> createProfile(String firstName, String lastName, int dni, String email, int age, String location, Long userId) {
        var profileId = profilesContextFacade.createProfile(firstName, lastName, dni, email, age, location, userId);
        return profileId == 0L ? Optional.empty() : Optional.of(new ProfileId(profileId));
    }
}
