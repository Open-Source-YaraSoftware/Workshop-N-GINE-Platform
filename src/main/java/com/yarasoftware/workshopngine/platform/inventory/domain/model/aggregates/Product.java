package com.yarasoftware.workshopngine.platform.inventory.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.CreateProductCommand;
import com.yarasoftware.workshopngine.platform.inventory.domain.model.commands.UpdateProductCommand;
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
public class Product extends AbstractAggregateRoot<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long stockQuantity;

    private Long lowStockThreshold;

    private Long workshopId;

    public Product() {
    }

    public Product(CreateProductCommand command) {
        name = command.name();
        description = command.description();
        stockQuantity = command.stockQuantity();
        lowStockThreshold = command.lowStockThreshold();
        workshopId = command.workshopId();
    }

    public void Update(UpdateProductCommand command)
    {
        name = command.name();
        description = command.description();
        stockQuantity = command.stockQuantity();
        lowStockThreshold = command.lowStockThreshold();
    }

    public Boolean IsAvailableRequest(Long quantity)
    {
        return stockQuantity >= quantity;
    }

    public void Request(Long quantity)
    {
        stockQuantity -= quantity;
    }



}
