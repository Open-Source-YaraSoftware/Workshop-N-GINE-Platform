package com.yarasoftware.workshopngine.platform.service.domain.services;
import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Workshop;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllClientsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllMechanicsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetWorkshopByIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

public interface WorkshopQueryService {
    Optional<Workshop> handle(GetWorkshopByIdQuery query);

    List<UserId> handle(GetAllClientsByWorkshopIdQuery query);

    List<UserId> handle(GetAllMechanicsByWorkshopIdQuery query);
}
