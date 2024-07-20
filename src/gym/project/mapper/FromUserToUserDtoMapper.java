package gym.project.mapper;

import gym.project.dto.UserDto;
import gym.project.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FromUserToUserDtoMapper implements Mapper<User, UserDto> {
    @Getter
    private static final FromUserToUserDtoMapper INSTANCE = new FromUserToUserDtoMapper();
    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .username(object.getUsername())
                .password(object.getPassword())
                .email(object.getEmail())
                .role(object.getRole().name())
                .build();
    }

    @Override
    public UserDto mapFromWithId(User object) {
        return null;
    }

}
