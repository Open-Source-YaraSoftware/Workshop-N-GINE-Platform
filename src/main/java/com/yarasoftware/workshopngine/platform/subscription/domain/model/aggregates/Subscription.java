package com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.valueobjects.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Getter
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "workshop_id")
    private Long workshopId;

    public static Subscription create(Plan plan, Long workshopId) {
        Subscription subscription = new Subscription();
        subscription.plan = plan;
        subscription.workshopId = workshopId;
        subscription.status = SubscriptionStatus.ACTIVE;
        subscription.startDate = LocalDateTime.now();
        subscription.endDate = subscription.startDate.plusMonths(plan.getDurationInMonths());
        return subscription;
    }

    public void renew() {
        this.endDate = this.endDate.plusMonths(plan.getDurationInMonths());
        this.status = SubscriptionStatus.ACTIVE;
    }

    public void cancel() {
        this.status = SubscriptionStatus.CANCELLED;
    }

    public boolean isActive() {
        return status == SubscriptionStatus.ACTIVE && LocalDateTime.now().isBefore(endDate);
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }
}