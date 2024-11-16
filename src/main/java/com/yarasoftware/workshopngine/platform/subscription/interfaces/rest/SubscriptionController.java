package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.CreateSubscriptionCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetSubscriptionByIdQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.SubscriptionCommandService;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.SubscriptionQueryService;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources.SubscriptionResource;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/memberships")
@RequiredArgsConstructor
@Tag(name = "Memberships", description = "Subscription management endpoints")
public class SubscriptionController {
    private final SubscriptionCommandService membershipCommandService;
    private final SubscriptionQueryService membershipQueryService;
    private final SubscriptionResourceFromEntityAssembler membershipAssembler;

    @Operation(summary = "Create new membership")
    @PostMapping
    public ResponseEntity<Long> createMembership(@RequestBody CreateSubscriptionCommand command) {
        Long membershipId = membershipCommandService.createMembership(command);
        return ResponseEntity.ok(membershipId);
    }

    @Operation(summary = "Renew existing membership")
    @PostMapping("/{id}/renew")
    public ResponseEntity<Void> renewMembership(@PathVariable Long id) {
        membershipCommandService.renewMembership(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get membership by ID")
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResource> getMembership(@PathVariable Long id) {
        return membershipQueryService.getMembershipById(new GetSubscriptionByIdQuery(id))
                .map(membership -> ResponseEntity.ok(membershipAssembler.toResource(membership)))
                .orElse(ResponseEntity.notFound().build());
    }
}