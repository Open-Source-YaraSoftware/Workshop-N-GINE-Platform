package com.yarasoftware.workshopngine.platform.subscription.application.internal.commandservices;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.CreatePlanCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates.Plan;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.PlanCommandService;
import com.yarasoftware.workshopngine.platform.subscription.infrastructure.persistence.jpa.repositories.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanCommandServiceImpl implements PlanCommandService {

    private final PlanRepository planRepository;

    public PlanCommandServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    @Transactional
    public Long createPlan(CreatePlanCommand command) {
        Plan plan = Plan.create(
                command.name(),
                command.description(),
                command.price(),
                command.durationInMonths(),
                command.billingCycle(),
                command.limitations()
        );
        Plan savedPlan = planRepository.save(plan);
        return savedPlan.getId();
    }

    @Override
    @Transactional
    public void updatePlan(Long planId, CreatePlanCommand command) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));
        plan.update(
                command.name(),
                command.description(),
                command.price(),
                command.durationInMonths(),
                command.billingCycle(),
                command.limitations()
        );
        planRepository.save(plan);
    }

    @Override
    @Transactional
    public void deletePlan(Long planId) {
        planRepository.deleteById(planId);
    }
}