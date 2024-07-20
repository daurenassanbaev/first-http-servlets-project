package gym.project.mapper;

import gym.project.dto.UserDto;
import gym.project.entity.Role;
import gym.project.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<UserDto, User> {
    @Getter
    private static final UserMapper INSTANCE = new UserMapper();
    @Override
    public User mapFrom(UserDto object) {
        return User.builder()
                .username(object.getUsername())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .email(object.getEmail())
                .build();
    }

    @Override
    public User mapFromWithId(UserDto object) {
        return null;
    }
}
