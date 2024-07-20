package gym.project.service;

import gym.project.dao.BookingDao;
import gym.project.dto.BookingDto;
import gym.project.entity.Booking;
import gym.project.entity.ShowBooking;
import gym.project.entity.ViewSchedule;
import gym.project.mapper.BookMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookService {
    private final BookMapper bookMapper = BookMapper.getINSTANCE();
    private final BookingDao bookingDao = BookingDao.getINSTANCE();
    @Getter
    private static final BookService INSTANCE = new BookService();
    @SneakyThrows
    public Integer book(BookingDto bookingDto) {
        Booking booking = bookMapper.mapFrom(bookingDto);
        Booking booking1 = bookingDao.save(booking);
        return booking1.getId();
    }
    public List<ViewSchedule> getAllScheduleJoin(Integer userId) {
        return bookingDao.getBookings(userId);
    }
    public List<ViewSchedule> getAllScheduleJoinView() {
        return bookingDao.getBookingsJoin();
    }
    public List<ShowBooking> getAdminBookings() {
        return bookingDao.getAdminBookings();
    }


    public void cancel(Integer id) {
        bookingDao.cancel(id);
    }
}
