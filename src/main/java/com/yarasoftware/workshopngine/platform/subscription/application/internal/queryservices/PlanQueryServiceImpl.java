package com.yarasoftware.workshopngine.platform.subscription.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Plan;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetPlanByIdQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.PlanQueryService;
import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanQueryServiceImpl implements PlanQueryService {
    private final PlanRepository paymentRepository;

    @Override
    public List<Plan> getPaymentHistory(GetPlanByIdQuery query) {
        return paymentRepository.findByMembershipId(query.membershipId());
    }

    @Override
    public List<Plan> getAllPayments() {
        return paymentRepository.findAll();
    }
}