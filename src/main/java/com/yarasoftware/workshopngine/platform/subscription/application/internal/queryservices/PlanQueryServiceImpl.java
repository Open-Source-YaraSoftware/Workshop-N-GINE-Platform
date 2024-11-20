package com.yarasoftware.workshopngine.platform.subscription.application.internal.queryservices;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Plan;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetPlanByIdQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.PlanQueryService;
import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlanQueryServiceImpl implements PlanQueryService {

    private final PlanRepository planRepository;

    public PlanQueryServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Plan> getPlanById(GetPlanByIdQuery query) {
        return planRepository.findById(query.id());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }
}