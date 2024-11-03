package com.yarasoftware.workshopngine.platform.payment.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories.MembershipRepository;
import com.yarasoftware.workshopngine.platform.payment.domain.commands.CreateMembershipCommand;
import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Membership;
import com.yarasoftware.workshopngine.platform.payment.domain.services.MembershipCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MembershipCommandServiceImpl implements MembershipCommandService {
    private final MembershipRepository membershipRepository;

    @Override
    @Transactional
    public Long createMembership(CreateMembershipCommand command) {
        Membership membership = Membership.create(
                command.type(),
                command.amount(),
                command.workshopId()
        );
        return membershipRepository.save(membership).getId();
    }

    @Override
    @Transactional
    public void renewMembership(Long membershipId) {
        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        membership.renew();
        membershipRepository.save(membership);
    }
}