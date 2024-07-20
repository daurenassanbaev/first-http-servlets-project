package gym.project.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BookingDto {
    Integer id;
    Integer userId;
    Integer scheduleId;
}
