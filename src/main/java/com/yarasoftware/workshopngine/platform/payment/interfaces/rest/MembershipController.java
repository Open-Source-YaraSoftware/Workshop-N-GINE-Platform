package com.yarasoftware.workshopngine.platform.payment.interfaces.rest;

import com.yarasoftware.workshopngine.platform.payment.domain.commands.CreateMembershipCommand;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetMembershipByIdQuery;
import com.yarasoftware.workshopngine.platform.payment.domain.services.MembershipCommandService;
import com.yarasoftware.workshopngine.platform.payment.domain.services.MembershipQueryService;
import com.yarasoftware.workshopngine.platform.payment.interfaces.rest.resources.MembershipResource;
import com.yarasoftware.workshopngine.platform.payment.interfaces.rest.transform.MembershipResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/memberships")
@RequiredArgsConstructor
@Tag(name = "Memberships", description = "Membership management endpoints")
public class MembershipController {
    private final MembershipCommandService membershipCommandService;
    private final MembershipQueryService membershipQueryService;
    private final MembershipResourceFromEntityAssembler membershipAssembler;

    @Operation(summary = "Create new membership")
    @PostMapping
    public ResponseEntity<Long> createMembership(@RequestBody CreateMembershipCommand command) {
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
    public ResponseEntity<MembershipResource> getMembership(@PathVariable Long id) {
        return membershipQueryService.getMembershipById(new GetMembershipByIdQuery(id))
                .map(membership -> ResponseEntity.ok(membershipAssembler.toResource(membership)))
                .orElse(ResponseEntity.notFound().build());
    }
}