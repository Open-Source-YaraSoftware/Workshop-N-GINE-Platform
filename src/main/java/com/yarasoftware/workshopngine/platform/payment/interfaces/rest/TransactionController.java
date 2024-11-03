package com.yarasoftware.workshopngine.platform.payment.interfaces.rest;

import com.yarasoftware.workshopngine.platform.payment.domain.commands.RegisterTransactionCommand;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetTransactionByIdQuery;
import com.yarasoftware.workshopngine.platform.payment.domain.queries.GetTransactionsByPaymentIdQuery;
import com.yarasoftware.workshopngine.platform.payment.domain.services.TransactionCommandService;
import com.yarasoftware.workshopngine.platform.payment.domain.services.TransactionQueryService;
import com.yarasoftware.workshopngine.platform.payment.interfaces.rest.resources.TransactionResource;
import com.yarasoftware.workshopngine.platform.payment.interfaces.rest.transform.TransactionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "Transaction management endpoints")
public class TransactionController {
    private final TransactionCommandService transactionCommandService;
    private final TransactionQueryService transactionQueryService;
    private final TransactionResourceFromEntityAssembler transactionAssembler;

    @Operation(summary = "Register new transaction")
    @PostMapping
    public ResponseEntity<Long> registerTransaction(@RequestBody RegisterTransactionCommand command) {
        Long transactionId = transactionCommandService.registerTransaction(command);
        return ResponseEntity.ok(transactionId);
    }

    @Operation(summary = "Approve transaction")
    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approveTransaction(@PathVariable Long id) {
        transactionCommandService.approveTransaction(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Decline transaction")
    @PostMapping("/{id}/decline")
    public ResponseEntity<Void> declineTransaction(@PathVariable Long id) {
        transactionCommandService.declineTransaction(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update transaction status")
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateTransactionStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        transactionCommandService.updateTransactionStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get transaction by ID")
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResource> getTransactionById(@PathVariable Long id) {
        return transactionQueryService.getTransactionById(new GetTransactionByIdQuery(id))
                .map(transaction -> ResponseEntity.ok(transactionAssembler.toResource(transaction)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get transactions by payment ID")
    @GetMapping("/by-payment/{paymentId}")
    public ResponseEntity<List<TransactionResource>> getTransactionsByPaymentId(@PathVariable Long paymentId) {
        List<TransactionResource> transactions = transactionQueryService.getTransactionsByPaymentId(new GetTransactionsByPaymentIdQuery(paymentId))
                .stream()
                .map(transactionAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactions);
    }
}