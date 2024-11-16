package com.yarasoftware.workshopngine.platform.subscription.interfaces.rest;

import com.yarasoftware.workshopngine.platform.subscription.domain.commands.CreateSubscriptionCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.commands.UpdateSubscriptionCommand;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetSubscriptionByIdQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.queries.GetAllSubscriptionsQuery;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.SubscriptionCommandService;
import com.yarasoftware.workshopngine.platform.subscription.domain.services.SubscriptionQueryService;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.resources.SubscriptionResource;
import com.yarasoftware.workshopngine.platform.subscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Subscription", description = "Subscription Management Endpoints")
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {
    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;
    private final SubscriptionResourceFromEntityAssembler subscriptionAssembler;

    public SubscriptionController(SubscriptionCommandService subscriptionCommandService,
                                  SubscriptionQueryService subscriptionQueryService,
                                  SubscriptionResourceFromEntityAssembler subscriptionAssembler) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
        this.subscriptionAssembler = subscriptionAssembler;
    }

    @PostMapping
    public ResponseEntity<Long> createSubscription(@RequestBody CreateSubscriptionCommand command) {
        Long subscriptionId = subscriptionCommandService.createSubscription(command);
        return ResponseEntity.ok(subscriptionId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSubscription(@PathVariable Long id, @RequestBody UpdateSubscriptionCommand command) {
        subscriptionCommandService.updateSubscription(new UpdateSubscriptionCommand(id, command.newStatus()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/renew")
    public ResponseEntity<Void> renewSubscription(@PathVariable Long id) {
        subscriptionCommandService.renewSubscription(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long id) {
        subscriptionCommandService.cancelSubscription(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResource> getSubscription(@PathVariable Long id) {
        return subscriptionQueryService.getSubscriptionById(new GetSubscriptionByIdQuery(id))
                .map(subscription -> ResponseEntity.ok(subscriptionAssembler.toResource(subscription)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResource>> getAllSubscriptions() {
        List<SubscriptionResource> subscriptions = subscriptionQueryService.getAllSubscriptions(new GetAllSubscriptionsQuery())
                .stream()
                .map(subscriptionAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subscriptions);
    }
}