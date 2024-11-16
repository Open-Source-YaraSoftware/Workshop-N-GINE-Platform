package com.yarasoftware.workshopngine.platform.subscription.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.subscription.domain.model.valueobjects.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "memberships")
@Getter
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_type")
    private SubscriptionStatus membershipType;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private Float amount;

    @Column(name = "workshop_id")
    private Long workshopId;

    public static Subscription create(SubscriptionStatus type, Float amount, Long workshopId) {
        Subscription membership = new Subscription();
        membership.membershipType = type;
        membership.amount = amount;
        membership.workshopId = workshopId;
        membership.startDate = LocalDateTime.now();
        membership.endDate = membership.startDate.plusMonths(1);
        return membership;
    }

    public void renew() {
        this.endDate = this.endDate.plusMonths(1);
    }

    public boolean isActive() {
        return LocalDateTime.now().isBefore(endDate);
    }
}