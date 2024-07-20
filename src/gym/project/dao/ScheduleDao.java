package gym.project.dao;

import gym.project.entity.Schedule;
import gym.project.entity.ViewReview;
import gym.project.exception.EmailValidationException;
import gym.project.util.ConnectionManager;
import lombok.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleDao implements Dao<Schedule> {
    @Getter
    private static final ScheduleDao INSTANCE = new ScheduleDao();
    private static final String SAVE_SQL = "INSERT INTO schedule (trainer_id, name, date, start_time, end_time, location) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_DATE_TIME_ALL_SQL = "SELECT date, start_time, end_time FROM schedule WHERE trainer_id = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM schedule";
    private static final String PARAM_SQL = "SELECT * FROM schedule WHERE status != 'BUSY'";
    private static final String DELETE_SQL = "DELETE FROM schedule WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM schedule WHERE id = ?";
    private static final String FIND_BY_ID_2_SQL = "SELECT * FROM schedule WHERE id != ?";
    private static final String UPDATE_SQL = "UPDATE schedule SET trainer_id = ?, name = ?, date = ?, start_time = ?, end_time = ?, location = ? WHERE id = ?";
    @SneakyThrows
    @Override
    public Schedule save(Schedule entity) {
        try (var connection = ConnectionManager.get();
        var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
        var ps2 = connection.prepareStatement(FIND_DATE_TIME_ALL_SQL)) {
            ps2.setObject(1, entity.getTrainerId());
            ResultSet resultSet = ps2.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getDate("date").equals(Date.valueOf(entity.getDate())) &&
                        resultSet.getTime("start_time").compareTo(Time.valueOf(entity.getStartTime())) <= 0
                && resultSet.getTime("end_time").compareTo(Time.valueOf(entity.getEndTime())) >= 0) {
                    throw new EmailValidationException("In this date and time trainer is busy. Please choose another time and date");
                }
            }
            preparedStatement.setObject(1, entity.getTrainerId());
            preparedStatement.setObject(2, entity.getName());
            preparedStatement.setObject(3, Date.valueOf(entity.getDate()));
            preparedStatement.setObject(4, Time.valueOf(entity.getStartTime()));
            preparedStatement.setObject(5, Time.valueOf(entity.getEndTime()));
            preparedStatement.setObject(6, entity.getLocation());
            preparedStatement.executeUpdate();
            ResultSet set = preparedStatement.getGeneratedKeys();
            if (set.next()) {
                entity.setId(set.getInt("id"));
            }
            return entity;
        }
    }
    @SneakyThrows
    @Override
    public Integer delete(Integer id) {
        try (var connection = ConnectionManager.get();
        var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setObject(1, id);
            return preparedStatement.executeUpdate();
        }
    }
    @SneakyThrows
    @Override
    public Schedule findById(Integer id) {
        try (var c = ConnectionManager.get();
        var ps = c.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setObject(1, id);
            var rs = ps.executeQuery();
            rs.next();
            return Schedule.builder()
                    .id(rs.getObject("id", Integer.class))
                    .trainerId(rs.getObject("trainer_id", Integer.class))
                    .name(rs.getString("name"))
                    .date(rs.getObject("date", LocalDate.class))
                    .startTime(rs.getObject("start_time", LocalTime.class))
                    .endTime(rs.getObject("end_time", LocalTime.class))
                    .location(rs.getString("location"))
                    .build();
        }
    }
    @SneakyThrows
    public Integer updateStatus(Integer id, String status) {
        try (var c = ConnectionManager.get();
        var ps = c.prepareStatement("UPDATE schedule SET status = ? WHERE id = ?")) {
            ps.setObject(1, status);
            ps.setObject(2, id);
            return ps.executeUpdate();
        }
    }
    @SneakyThrows
    @Override
    public Integer update(Schedule entity) {
        try (var connection = ConnectionManager.get();
             var ps = connection.prepareStatement(UPDATE_SQL);
             var ps2 = connection.prepareStatement(FIND_BY_ID_2_SQL)) {
            ps2.setObject(1, entity.getTrainerId());
            ResultSet resultSet = ps2.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getDate("date").equals(Date.valueOf(entity.getDate())) &&
                        resultSet.getTime("start_time").compareTo(Time.valueOf(entity.getStartTime())) <= 0
                        && resultSet.getTime("end_time").compareTo(Time.valueOf(entity.getEndTime())) >= 0
                && resultSet.getObject("trainer_id", Integer.class).equals(entity.getTrainerId())) {
                    throw new EmailValidationException("In this date and time trainer is busy. Please choose another time and date");
                }
            }
            ps.setObject(1, entity.getTrainerId());
            ps.setObject(2, entity.getName());
            ps.setObject(3, entity.getDate());
            ps.setObject(4, entity.getStartTime());
            ps.setObject(5, entity.getEndTime());
            ps.setObject(6, entity.getLocation());
            ps.setObject(7, entity.getId());
            return ps.executeUpdate();
        }
    }
    @SneakyThrows
    @Override
    public List<Schedule> findAll() {
        List<Schedule> result = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
        var ps = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Schedule schedule = Schedule.builder()
                        .id(rs.getObject("id", Integer.class))
                        .trainerId(rs.getObject("trainer_id", Integer.class))
                        .name(rs.getString("name"))
                        .date(rs.getObject("date", LocalDate.class))
                        .startTime(rs.getObject("start_time", LocalTime.class))
                        .endTime(rs.getObject("end_time", LocalTime.class))
                        .location(rs.getString("location"))
                        .build();
                result.add(schedule);

            }
            return result;
        }
    }
    @SneakyThrows
    public List<Schedule> findAllForBooking() {
        List<Schedule> result = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
        var ps = connection.prepareStatement(PARAM_SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Schedule schedule = Schedule.builder()
                        .id(rs.getObject("id", Integer.class))
                        .trainerId(rs.getObject("trainer_id", Integer.class))
                        .name(rs.getString("name"))
                        .date(rs.getObject("date", LocalDate.class))
                        .startTime(rs.getObject("start_time", LocalTime.class))
                        .endTime(rs.getObject("end_time", LocalTime.class))
                        .location(rs.getString("location"))
                        .build();
                result.add(schedule);

            }
            return result;
        }
    }

}
