package com.yarasoftware.workshopngine.platform.payment.domain.services;

import com.yarasoftware.workshopngine.platform.payment.domain.commands.CreateMembershipCommand;

public interface MembershipCommandService {
    Long createMembership(CreateMembershipCommand command);
    void renewMembership(Long membershipId);
}