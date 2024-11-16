package com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.valueobjects.BillingCycle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "plans")
@Getter
@NoArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer price;
    private Integer durationInMonths;

    @Enumerated(EnumType.STRING)
    private BillingCycle billingCycle;

    @ElementCollection
    private List<String> limitations;

    public static Plan create(String name, String description, Integer price, Integer durationInMonths, BillingCycle billingCycle, List<String> limitations) {
        Plan plan = new Plan();
        plan.name = name;
        plan.description = description;
        plan.price = price;
        plan.durationInMonths = durationInMonths;
        plan.billingCycle = billingCycle;
        plan.limitations = limitations;
        return plan;
    }

    public void update(String name, String description, Integer price, Integer durationInMonths, BillingCycle billingCycle, List<String> limitations) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationInMonths = durationInMonths;
        this.billingCycle = billingCycle;
        this.limitations = limitations;
    }
}