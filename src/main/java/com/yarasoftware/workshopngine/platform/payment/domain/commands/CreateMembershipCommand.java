package com.yarasoftware.workshopngine.platform.payment.domain.commands;

import com.yarasoftware.workshopngine.platform.payment.domain.model.valueobjects.MembershipType;

public record CreateMembershipCommand(
        MembershipType type,
        Float amount,
        Long workshopId,
        String paymentMethod
) {}