package com.yarasoftware.workshopngine.platform.billing.interfaces.rest;

import com.yarasoftware.workshopngine.platform.billing.domain.commands.CreateInvoiceCommand;
import com.yarasoftware.workshopngine.platform.billing.domain.commands.UpdateInvoiceStatusCommand;
import com.yarasoftware.workshopngine.platform.billing.domain.queries.GetInvoiceByIdQuery;
import com.yarasoftware.workshopngine.platform.billing.domain.queries.GetInvoicesBySubscriptionIdQuery;
import com.yarasoftware.workshopngine.platform.billing.domain.services.InvoiceCommandService;
import com.yarasoftware.workshopngine.platform.billing.domain.services.InvoiceQueryService;
import com.yarasoftware.workshopngine.platform.billing.interfaces.rest.resources.InvoiceResource;
import com.yarasoftware.workshopngine.platform.billing.interfaces.rest.transform.InvoiceResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Invoice", description = "Invoice Management Endpoints")
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceCommandService invoiceCommandService;
    private final InvoiceQueryService invoiceQueryService;
    private final InvoiceResourceFromEntityAssembler invoiceAssembler;

    public InvoiceController(InvoiceCommandService invoiceCommandService,
                             InvoiceQueryService invoiceQueryService,
                             InvoiceResourceFromEntityAssembler invoiceAssembler) {
        this.invoiceCommandService = invoiceCommandService;
        this.invoiceQueryService = invoiceQueryService;
        this.invoiceAssembler = invoiceAssembler;
    }

    @PostMapping
    public ResponseEntity<Long> createInvoice(@RequestBody CreateInvoiceCommand command) {
        Long invoiceId = invoiceCommandService.createInvoice(command);
        return ResponseEntity.ok(invoiceId);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateInvoiceStatus(@PathVariable Long id, @RequestBody UpdateInvoiceStatusCommand command) {
        invoiceCommandService.updateInvoiceStatus(new UpdateInvoiceStatusCommand(id, command.newStatus()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResource> getInvoice(@PathVariable Long id) {
        return invoiceQueryService.getInvoiceById(new GetInvoiceByIdQuery(id))
                .map(invoice -> ResponseEntity.ok(invoiceAssembler.toResource(invoice)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/subscription/{subscriptionId}")
    public ResponseEntity<List<InvoiceResource>> getInvoicesBySubscriptionId(@PathVariable Long subscriptionId) {
        List<InvoiceResource> invoices = invoiceQueryService.getInvoicesBySubscriptionId(new GetInvoicesBySubscriptionIdQuery(subscriptionId))
                .stream()
                .map(invoiceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(invoices);
    }
}