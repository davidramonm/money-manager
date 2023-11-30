package money.manager.service.activity.implementation;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Collections2;

import money.manager.domain.activity.Activity;
import money.manager.domain.activity.type.Type.ActivityType;
import money.manager.domain.gateway.ActivityGateway;
import money.manager.service.activity.ActivityService;
import money.manager.service.activity.dto.InsertActivityOutputDto;
import money.manager.service.activity.dto.InsertActivityInputDto;
import money.manager.service.activity.dto.ListActivitiesOutputDto;
import money.manager.service.activity.dto.mapper.ActivityToInsertActivityOutputMapper;
import money.manager.service.activity.dto.mapper.ActivityToListActivityesOutputMapper;
import money.manager.service.activity.dto.mapper.InserAcitivityInputToActivityMapper;

public class ActivityServiceImplementation implements ActivityService {

    private ActivityGateway activityGateway;

    private ActivityServiceImplementation(final ActivityGateway aGateway) {
        this.activityGateway = aGateway;
    }

    public static ActivityServiceImplementation build(final ActivityGateway aGateway) {
        return new ActivityServiceImplementation(aGateway);
    }

    @Override
    public InsertActivityOutputDto insertActivity(final InsertActivityInputDto anInput) {
        final var anActivity = InserAcitivityInputToActivityMapper.build().apply(anInput);

        this.activityGateway.create(anActivity);
        return ActivityToInsertActivityOutputMapper.build().apply(anActivity);

    }

    @Override
    public void removeActivity(String anId) {
        this.activityGateway.delete(anId);
    }

    @Override
    public List<InsertActivityOutputDto> listActivities() {
        final var aList = this.activityGateway.findAll();

        return aList.stream()
                .map(a -> ActivityToListActivityesOutputMapper.build().apply(a))
                .collect(Collectors.toList());
    }

    @Override
    public float calculateBalance() {
        final var aList = this.activityGateway.findAll();

        if (aList == null || aList.size() == 0) {
            return 0;
        }

        return (float) aList.stream().mapToDouble(
                a -> a.getType() == ActivityType.REVENUE
                        ? a.getValue()
                        : -a.getValue())
                .sum();
    }

}
