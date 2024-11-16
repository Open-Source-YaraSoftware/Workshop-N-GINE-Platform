package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.UpdateSubscriptionCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetPlanByIdQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.PlanCommandService;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.PlanQueryService;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources.PlanResource;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.transform.PlanResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payments", description = "Plan processing endpoints")
public class PlanController {
    private final PlanCommandService paymentCommandService;
    private final PlanQueryService paymentQueryService;
    private final PlanResourceFromEntityAssembler paymentAssembler;

    @Operation(summary = "Process payment")
    @PostMapping
    public ResponseEntity<Long> processPayment(@RequestBody UpdateSubscriptionCommand command) {
        Long paymentId = paymentCommandService.processPayment(command);
        return ResponseEntity.ok(paymentId);
    }

    @Operation(summary = "Cancel payment")
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelPayment(@PathVariable Long id) {
        paymentCommandService.cancelPayment(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get payment history for membership")
    @GetMapping("/history/{membershipId}")
    public ResponseEntity<List<PlanResource>> getPaymentHistory(@PathVariable Long membershipId) {
        List<PlanResource> history = paymentQueryService.getPaymentHistory(new GetPlanByIdQuery(membershipId))
                .stream()
                .map(paymentAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(history);
    }

    @Operation(summary = "Get all payments")
    @GetMapping
    public ResponseEntity<List<PlanResource>> getAllPayments() {
        List<PlanResource> payments = paymentQueryService.getAllPayments()
                .stream()
                .map(paymentAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(payments);
    }
}