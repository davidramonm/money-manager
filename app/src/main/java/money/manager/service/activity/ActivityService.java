package money.manager.service.activity;

import java.util.List;

import money.manager.service.activity.dto.InsertActiviesOutputDto;
import money.manager.service.activity.dto.InsertActivityInputDto;
import money.manager.service.activity.dto.ListActiviesOutputDto;

public interface ActivityService {
    
    public InsertActiviesOutputDto insertActivity(InsertActivityInputDto anInput);
    public void removeActivity(final String anId);
    public List<ListActiviesOutputDto> listActivities();
    public void calculateBalance();

}
