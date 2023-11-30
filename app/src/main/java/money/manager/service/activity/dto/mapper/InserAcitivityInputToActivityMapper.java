package money.manager.service.activity.dto.mapper;

import com.google.common.base.Function;

import money.manager.domain.activity.Activity;
import money.manager.domain.activity.type.Type.ActivityType;
import money.manager.service.activity.dto.InsertActivityInputDto;
import money.manager.service.exception.ServiceException;

public class InserAcitivityInputToActivityMapper implements Function<InsertActivityInputDto, Activity> {

    public static InserAcitivityInputToActivityMapper build() {
        return new InserAcitivityInputToActivityMapper();
    }

    @Override
    public Activity apply(final InsertActivityInputDto input) {

        if (input.type().equals(ActivityType.REVENUE)) {
            final var anActivity = Activity.newActivity(input.date(), input.description(), input.value(),
                    ActivityType.REVENUE);
            return anActivity;
        } else if (input.type().equals(ActivityType.EXPENSE)) {
            final var anActivity = Activity.newActivity(input.date(), input.description(), input.value(),
                    ActivityType.EXPENSE);
        }
        else{
            throw new ServiceException("Invalid activity type");
        }
        return null;

    }

}
