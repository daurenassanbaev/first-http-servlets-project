package gym.project.mapper;

import gym.project.dto.BookingDto;
import gym.project.dto.UserDto;
import gym.project.entity.Booking;
import gym.project.entity.Role;
import gym.project.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookMapper implements Mapper<BookingDto, Booking> {
    @Getter
    private static final BookMapper INSTANCE = new BookMapper();
    @Override
    public Booking mapFrom(BookingDto object) {
        return Booking.builder()
                .userId(object.getUserId())
                .scheduleId(object.getScheduleId())
                .build();
    }

    @Override
    public Booking mapFromWithId(BookingDto object) {
        return null;
    }
}
