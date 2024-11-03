package com.yarasoftware.workshopngine.platform.payment.interfaces.rest;

import com.yarasoftware.workshopngine.platform.payment.domain.commands.ProcessPaymentCommand;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetPaymentHistoryQuery;
import com.yarasoftware.workshopngine.platform.payment.domain.services.PaymentCommandService;
import com.yarasoftware.workshopngine.platform.payment.domain.services.PaymentQueryService;
import com.yarasoftware.workshopngine.platform.payment.interfaces.rest.resources.PaymentResource;
import com.yarasoftware.workshopngine.platform.payment.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
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
@Tag(name = "Payments", description = "Payment processing endpoints")
public class PaymentController {
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;
    private final PaymentResourceFromEntityAssembler paymentAssembler;

    @Operation(summary = "Process payment")
    @PostMapping
    public ResponseEntity<Long> processPayment(@RequestBody ProcessPaymentCommand command) {
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
    public ResponseEntity<List<PaymentResource>> getPaymentHistory(@PathVariable Long membershipId) {
        List<PaymentResource> history = paymentQueryService.getPaymentHistory(new GetPaymentHistoryQuery(membershipId))
                .stream()
                .map(paymentAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(history);
    }

    @Operation(summary = "Get all payments")
    @GetMapping
    public ResponseEntity<List<PaymentResource>> getAllPayments() {
        List<PaymentResource> payments = paymentQueryService.getAllPayments()
                .stream()
                .map(paymentAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(payments);
    }
}