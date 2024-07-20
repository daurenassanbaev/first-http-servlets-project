package gym.project.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Value
public class ScheduleDto {
    Integer id;
    Integer trainerId;
    String name;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    String location;
}
