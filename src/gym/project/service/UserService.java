package gym.project.service;
import gym.project.dao.BookingDao;
import gym.project.dao.ScheduleDao;
import gym.project.dao.UserDao;
import gym.project.dto.BookingDto;
import gym.project.dto.UserDto;

import gym.project.entity.*;
import gym.project.exception.EmailValidationException;
import gym.project.mapper.BookMapper;
import gym.project.mapper.FromUserToUserDtoMapper;
import gym.project.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    @Getter
    private static final UserService INSTANCE = new UserService();
    private static final UserMapper userMapper = UserMapper.getINSTANCE();
    private static final BookMapper bookMapper = BookMapper.getINSTANCE();
    private static final FromUserToUserDtoMapper userDtoMapper = FromUserToUserDtoMapper.getINSTANCE();
    private final ScheduleDao scheduleDao = ScheduleDao.getINSTANCE();
    private static final UserDao userDao = UserDao.getUserDao();
    private static final BookingDao bookingDao = BookingDao.getINSTANCE();
    @SneakyThrows
    public Integer create(UserDto userDto) {
        User user = userMapper.mapFrom(userDto);
        User user1 = userDao.save(user);
        return user1.getId();
    }
    @SneakyThrows
    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password).map(userDtoMapper::mapFrom);
    }
    public List<User> getAllUsersExceptId(Integer id) {
        return userDao.findAllExceptId(id);
    }

    public Integer deleteUser(Integer id) {
        return userDao.delete(id);
    }
}
