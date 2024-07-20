package gym.project.dao;

import gym.project.entity.Booking;
import gym.project.entity.ShowBooking;
import gym.project.entity.ViewReview;
import gym.project.entity.ViewSchedule;
import gym.project.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingDao implements Dao<Booking>{
    @Getter
    private static final BookingDao INSTANCE = new BookingDao();
    private static final String SCHEDULE_SQL = "SELECT s.id, t.name as trainer_name, s.name as program_name, s.date, s.start_time, s.end_time, s.location, b.id as booking_id, s.trainer_id as trainer_id FROM schedule as s INNER JOIN trainers as t ON t.id = s.trainer_id INNER JOIN bookings as b ON s.id = b.schedule_id WHERE b.user_id = ?";
    private static final String SCHEDULE2_SQL = "SELECT b.id as booking_id, u.username, u.email, t.name as trainer_name, s.name as program_name, s.date, s.start_time, s.end_time, s.location FROM bookings b INNER JOIN schedule s ON b.schedule_id = s.id INNER JOIN users u on b.user_id = u.id INNER JOIN trainers t ON s.trainer_id = t.id;";
    private static final String SCHEDULE1_SQL = "SELECT s.id, t.name as trainer_name, s.name as program_name, s.date, s.start_time, s.end_time, s.location FROM schedule as s INNER JOIN trainers as t ON t.id = s.trainer_id WHERE s.status = 'FREE'";
    private static final String SAVE_SQL = "INSERT INTO bookings (user_id, schedule_id) VALUES (?, ?)";
    private static final String FIND_SQL = "SELECT schedule_id FROM bookings WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM bookings WHERE id = ?";
    private static final ScheduleDao schDao = ScheduleDao.getINSTANCE();
    @SneakyThrows
    @Override
    public Booking save(Booking entity) {
        try (var c = ConnectionManager.get();
        var stm = c.prepareStatement(SAVE_SQL)) {
            stm.setObject(1, entity.getUserId());
            stm.setObject(2, entity.getScheduleId());
            ScheduleDao scheduleDao = ScheduleDao.getINSTANCE();
            scheduleDao.updateStatus(entity.getScheduleId(), "BUSY");
            stm.executeUpdate();
            return entity;
        }
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }

    @Override
    public Booking findById(Integer id) {
        return null;
    }

    @Override
    public Integer update(Booking id) {
        return 0;
    }

    @Override
    public List<Booking> findAll() {
        return List.of();
    }
    @SneakyThrows
    public List<ViewSchedule> getBookings(Integer userId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(SCHEDULE_SQL)) {
            List<ViewSchedule> schedules = new ArrayList<>();
            statement.setObject(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                schedules.add(ViewSchedule.builder()
                        .id(resultSet.getObject("id", Integer.class))
                        .trainerName(resultSet.getString("trainer_name"))
                        .programName(resultSet.getString("program_name"))
                        .date(resultSet.getObject("date", LocalDate.class))
                        .startTime(resultSet.getObject("start_time", LocalTime.class))
                        .endTime(resultSet.getObject("end_time", LocalTime.class))
                        .location(resultSet.getString("location"))
                                .bookingId(resultSet.getObject("booking_id", Integer.class))
                                .trainerId(resultSet.getObject("trainer_id", Integer.class))
                        .build());
            }
            return schedules;
        }
    }
    @SneakyThrows
    public List<ViewSchedule> getBookingsJoin() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(SCHEDULE1_SQL)) {
            List<ViewSchedule> schedules = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                schedules.add(ViewSchedule.builder()
                        .id(resultSet.getObject("id", Integer.class))
                        .trainerName(resultSet.getString("trainer_name"))
                        .programName(resultSet.getString("program_name"))
                        .date(resultSet.getObject("date", LocalDate.class))
                        .startTime(resultSet.getObject("start_time", LocalTime.class))
                        .endTime(resultSet.getObject("end_time", LocalTime.class))
                        .location(resultSet.getString("location"))
                        .build());
            }
            return schedules;
        }
    }
    @SneakyThrows
    public void cancel(Integer id) {
        try (var c = ConnectionManager.get();
             var stm = c.prepareStatement(FIND_SQL);
             var s = c.prepareStatement(DELETE_SQL)) {
            stm.setObject(1, id);
            var rs = stm.executeQuery();
            rs.next();
            Integer scheduleId = rs.getObject("schedule_id", Integer.class);
            schDao.updateStatus(scheduleId, "FREE");
            s.setObject(1, id);
            s.executeUpdate();
        }
    }


    @SneakyThrows
    public List<ShowBooking> getAdminBookings() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(SCHEDULE2_SQL)) {
            List<ShowBooking> schedules = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                schedules.add(ShowBooking.builder()
                                .bookingId(resultSet.getObject("booking_id", Integer.class))
                                .username(resultSet.getString("username"))
                                .email(resultSet.getString("email"))
                                .trainerName(resultSet.getString("trainer_name"))
                                .programName(resultSet.getString("program_name"))
                                .date(resultSet.getObject("date", LocalDate.class))
                                .startTime(resultSet.getObject("start_time", LocalTime.class))
                                .endTime(resultSet.getObject("end_time", LocalTime.class))
                                .location(resultSet.getString("location"))
                        .build());
            }
            return schedules;
        }
    }
}
