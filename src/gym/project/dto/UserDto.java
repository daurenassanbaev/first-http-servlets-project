package gym.project.dto;

import gym.project.entity.Role;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserDto {
    Integer id;
    String username;
    String password;
    String email;
    String role;
}
