package com.yarasoftware.workshopngine.platform.payment.domain.model.aggregates;

import com.yarasoftware.workshopngine.platform.payment.domain.model.valueobjects.MembershipType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "memberships")
@Getter
@NoArgsConstructor
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_type")
    private MembershipType membershipType;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private Float amount;

    @Column(name = "workshop_id")
    private Long workshopId;

    public static Membership create(MembershipType type, Float amount, Long workshopId) {
        Membership membership = new Membership();
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