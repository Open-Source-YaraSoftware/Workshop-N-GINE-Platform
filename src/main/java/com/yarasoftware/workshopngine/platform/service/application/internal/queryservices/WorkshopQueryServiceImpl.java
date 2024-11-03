package com.yarasoftware.workshopngine.platform.service.application.internal.queryservices;
import com.yarasoftware.workshopngine.platform.service.application.internal.outboundservices.acl.ExternalIamService;
import com.yarasoftware.workshopngine.platform.service.domain.model.aggregates.Workshop;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllClientsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetAllMechanicsByWorkshopIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.queries.GetWorkshopByIdQuery;
import com.yarasoftware.workshopngine.platform.service.domain.model.valueobjects.UserId;
import com.yarasoftware.workshopngine.platform.service.domain.services.WorkshopQueryService;
import com.yarasoftware.workshopngine.platform.service.infrastructure.persistence.jpa.repositories.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopQueryServiceImpl implements WorkshopQueryService {
    private final WorkshopRepository workshopRepository;
    private final ExternalIamService externalIamService;

    public WorkshopQueryServiceImpl(WorkshopRepository workshopRepository, ExternalIamService externalIamService) {
        this.workshopRepository = workshopRepository;
        this.externalIamService = externalIamService;
    }

    @Override
    public Optional<Workshop> handle(GetWorkshopByIdQuery query) {
        return workshopRepository.findById(query.workshopId());
    }

    @Override
    public List<UserId> handle(GetAllClientsByWorkshopIdQuery query) {
        if (!workshopRepository.existsById(query.workshopId())) throw new IllegalArgumentException("Workshop does not exist");
        return externalIamService.fetchAllUsersByWorkshopIdAndRoleIsClient(query.workshopId());
    }

    @Override
    public List<UserId> handle(GetAllMechanicsByWorkshopIdQuery query) {
        if (!workshopRepository.existsById(query.workshopId())) throw new IllegalArgumentException("Workshop does not exist");
        return externalIamService.fetchAllUsersByWorkshopIdAndRoleIsMechanic(query.workshopId());
    }
}
