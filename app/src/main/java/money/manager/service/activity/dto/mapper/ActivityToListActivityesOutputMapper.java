package money.manager.service.activity.dto.mapper;

import com.google.common.base.Function;

import money.manager.domain.activity.Activity;
import money.manager.service.activity.dto.ListActivitiesOutputDto;

public class ActivityToListActivityesOutputMapper implements Function<Activity, ListActivitiesOutputDto> {

    public static ActivityToInsertActivityOutputMapper build() {
        return new ActivityToInsertActivityOutputMapper();
    }

    @Override
    public ListActivitiesOutputDto apply(final Activity input) {
        return new ListActivitiesOutputDto(input.getId(), input.getDate(), input.getDescription(), input.getValue(), input.getType().getValue(), input.getCreatedAt(), input.getUpdatedAt());


    }

}
