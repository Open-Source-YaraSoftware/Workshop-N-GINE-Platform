package com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.CreateProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.UpdateProductRequestCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.valueobjects.ProductRequestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Setter
@Entity
public class ProductRequest extends AbstractAggregateRoot<ProductRequest>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestedQuantity;

    private Long taskId;

    private Long productId;

    private Long workshopId;

    private ProductRequestStatus status;

    public ProductRequest() {
    }

    public ProductRequest(CreateProductRequestCommand command) {
        requestedQuantity = command.requestedQuantity();
        taskId = command.taskId();
        productId = command.productId();
        workshopId = command.workshopId();
        status = ProductRequestStatus.PENDING;
    }

    public void Update(UpdateProductRequestCommand command)
    {
        this.requestedQuantity = command.requestedQuantity();
        this.productId = command.productId();
    }

    public void Accept()
    {
        this.status = ProductRequestStatus.ACCEPTED;
    }

    public void Reject()
    {
        this.status = ProductRequestStatus.REJECTED;
    }

    public String StatusToString() {
        return switch (this.status) {
            case PENDING -> "Pending";
            case ACCEPTED -> "Accepted";
            case REJECTED -> "Rejected";
            default -> "Pending";
        };
    }


}
