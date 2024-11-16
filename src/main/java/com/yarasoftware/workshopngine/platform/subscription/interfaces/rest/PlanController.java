package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.CreatePlanCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetPlanByIdQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.PlanCommandService;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.PlanQueryService;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources.PlanResource;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.transform.PlanResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Plan", description = "Plan Management Endpoints")
@RequestMapping("/api/v1/plans")
public class PlanController {
    private final PlanCommandService planCommandService;
    private final PlanQueryService planQueryService;
    private final PlanResourceFromEntityAssembler planAssembler;

    public PlanController(PlanCommandService planCommandService,
                          PlanQueryService planQueryService,
                          PlanResourceFromEntityAssembler planAssembler) {
        this.planCommandService = planCommandService;
        this.planQueryService = planQueryService;
        this.planAssembler = planAssembler;
    }

    @PostMapping
    public ResponseEntity<Long> createPlan(@RequestBody CreatePlanCommand command) {
        Long planId = planCommandService.createPlan(command);
        return ResponseEntity.ok(planId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePlan(@PathVariable Long id, @RequestBody CreatePlanCommand command) {
        planCommandService.updatePlan(id, command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planCommandService.deletePlan(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResource> getPlan(@PathVariable Long id) {
        return planQueryService.getPlanById(new GetPlanByIdQuery(id))
                .map(plan -> ResponseEntity.ok(planAssembler.toResource(plan)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PlanResource>> getAllPlans() {
        List<PlanResource> plans = planQueryService.getAllPlans()
                .stream()
                .map(planAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(plans);
    }
}