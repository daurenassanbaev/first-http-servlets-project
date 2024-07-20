package gym.project.service;

import gym.project.dao.ScheduleDao;
import gym.project.dao.TrainerDao;
import gym.project.dto.ScheduleDto;
import gym.project.dto.TrainerDto;
import gym.project.entity.Schedule;
import gym.project.entity.Trainer;
import gym.project.mapper.ScheduleMapper;
import gym.project.mapper.TrainerMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.awt.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class AdminService {
    @Getter

    private static final AdminService INSTANCE = new AdminService();
    private static final TrainerDao trainerDao = new TrainerDao();
    private static final ScheduleDao scheduleDao = ScheduleDao.getINSTANCE();
    private final ImageService imageService = ImageService.getINSTANCE();
    private final TrainerMapper trainerMapper = TrainerMapper.getINSTANCE();
    private final ScheduleMapper scheduleMapper = ScheduleMapper.getINSTANCE();
    @SneakyThrows
    public Integer createTrainer(TrainerDto trainerDto) {
        Trainer trainer = trainerMapper.mapFrom(trainerDto);
        imageService.upload(trainer.getPhoto(), trainerDto.getPhoto().getInputStream());
        trainerDao.save(trainer);
        return trainer.getId();
    }
    @SneakyThrows
    public Integer createSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleMapper.mapFrom(scheduleDto);
        scheduleDao.save(schedule);
        return schedule.getId();
    }
    public List<Trainer> getAllTrainers() {
        return trainerDao.findAll();
    }
    public List<Schedule> getAllSchedulesParam() {
        return scheduleDao.findAllForBooking();
    }
    public List<Schedule> getAllSchedules() {
        return scheduleDao.findAll();
    }
    public Integer deleteTrainer(Integer id) {
        Integer result = trainerDao.delete(id);
        return result;
    }
    public Trainer findTrainerById(Integer id) {
        return trainerDao.findById(id);
    }
    @SneakyThrows
    public Integer updateTrainer(TrainerDto trainerDto) {
        Trainer trainer = trainerMapper.mapFromWithId(trainerDto);
        imageService.upload(trainer.getPhoto(), trainerDto.getPhoto().getInputStream());
        return trainerDao.update(trainer);
    }
    @SneakyThrows
    public Integer updateSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleMapper.mapFromWithId(scheduleDto);
        return scheduleDao.update(schedule);
    }

    public Integer deleteSchedule(Integer id) {
        Integer result = scheduleDao.delete(id);
        return result;
    }

    public Schedule findScheduleById(Integer scheduleId) {
        return scheduleDao.findById(scheduleId);
    }
}
