package gym.project.dao;

import gym.project.entity.*;
import gym.project.exception.EmailValidationException;
import gym.project.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<User> {
    @Getter
    private static final UserDao userDao = new UserDao();
    private static final String SAVE_SQL = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
    private static final String FIND_ALL_EMAIL_SQL = "SELECT email FROM users";
    private static final String FIND_USER_SQL = "SELECT id, username, email, password, role FROM users WHERE email = ? AND password = ?";
    private static final String FIND_ALL_EXCEPT_ID = "SELECT id, username, email, password, role FROM users WHERE id != ?";
    private static final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
    @SneakyThrows
    @Override
    public User save(User entity) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_EMAIL_SQL)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                if (entity.getEmail().equals(resultSet.getString("email"))) {
                    throw new EmailValidationException("This email is exists");
                }
            }
            statement.setObject(1, entity.getUsername());
            statement.setObject(2, entity.getPassword());
            statement.setObject(3, entity.getEmail());
            statement.setObject(4, entity.getRole().name());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
            return entity;
        }
    }
    @SneakyThrows
    @Override
    public Integer delete(Integer id) {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setObject(1, id);
            return statement.executeUpdate();
        }
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public Integer update(User entity) {
        return 0;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
    @SneakyThrows
    public List<User> findAllExceptId(Integer id) {
        try (var connection = ConnectionManager.get();
        PreparedStatement statement = connection.prepareStatement(FIND_ALL_EXCEPT_ID)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                users.add(user);
            }
            return users;
        }
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_SQL)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getObject("id", Integer.class))
                        .email(resultSet.getObject("email", String.class))
                        .username(resultSet.getObject("username", String.class))
                        .password(resultSet.getObject("password", String.class))
                        .role(Role.valueOf(resultSet.getObject("role", String.class)))
                        .build();
            }
            return Optional.ofNullable(user);
        }

    }

}
