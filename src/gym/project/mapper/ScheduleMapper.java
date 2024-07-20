package gym.project.mapper;

import gym.project.dto.ScheduleDto;
import gym.project.dto.TrainerDto;
import gym.project.entity.Schedule;
import gym.project.entity.Trainer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleMapper implements Mapper<ScheduleDto, Schedule> {
    @Getter
    private static final ScheduleMapper INSTANCE = new ScheduleMapper();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public Schedule mapFrom(ScheduleDto object) {
        return Schedule.builder()
                .name(object.getName())
                .trainerId(object.getTrainerId())
                .location(object.getLocation())
                .date(object.getDate())
                .startTime(object.getStartTime())
                .endTime(object.getEndTime())
                .build();
    }

    @Override
    public Schedule mapFromWithId(ScheduleDto object) {
        return Schedule.builder()
                .id(object.getId())
                .name(object.getName())
                .trainerId(object.getTrainerId())
                .location(object.getLocation())
                .date(object.getDate())
                .startTime(object.getStartTime())
                .endTime(object.getEndTime())
                .build();
    }
}
