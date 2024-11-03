package com.yarasoftware.workshopngine.platform.payment.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.payment.infrastructure.persistence.jpa.repositories.MembershipRepository;
import com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates.Membership;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetMembershipByIdQuery;
import com.yarasoftware.workshopngine.platform.payment.domain.services.MembershipQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembershipQueryServiceImpl implements MembershipQueryService {
    private final MembershipRepository membershipRepository;

    @Override
    public Optional<Membership> getMembershipById(GetMembershipByIdQuery query) {
        return membershipRepository.findById(query.id());
    }
}