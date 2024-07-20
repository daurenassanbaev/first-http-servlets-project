package gym.project.dao;

import gym.project.entity.Trainer;
import gym.project.entity.ViewReview;
import gym.project.exception.TrainerValidationException;
import gym.project.exception.EmailValidationException;
import gym.project.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainerDao implements Dao<Trainer>{
    private static final String SAVE_SQL = "INSERT INTO trainers (name, bio, contact_phone, contact_email, photo) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_ALL_SQL = "SELECT contact_phone, contact_email FROM trainers";
    private static final String FIND_ALL_WITH_FILTER_SQL = "SELECT contact_phone, contact_email FROM trainers WHERE id != ?";
    private static final String UPDATE_SQL = "UPDATE trainers SET name = ?, bio = ?, contact_phone = ?, contact_email = ?, photo=? WHERE id = ?";
    private static final String FIND_ALL_TRAINERS_SQL = "SELECT * FROM trainers";
    private static final String DELETE_SQL = "DELETE FROM trainers WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM trainers WHERE id = ?";

    @SneakyThrows
    @Override
    public Trainer save(Trainer entity) {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement ps2 = connection.prepareStatement(FIND_ALL_SQL);) {
            ResultSet resultSet = ps2.executeQuery();
            while (resultSet.next()) {
                if (entity.getContactEmail().equals(resultSet.getString("contact_email")) || entity.getContactPhone().equals(resultSet.getString("contact_phone"))) {
                    throw new TrainerValidationException("This email or phone is exists");
                }
            }
            ps.setObject(1, entity.getName());
            ps.setObject(2, entity.getBio());
            ps.setObject(3, entity.getContactPhone());
            ps.setObject(4, entity.getContactEmail());
            ps.setObject(5, entity.getPhoto());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getObject("id", Integer.class));
            }
            return entity;
        }
    }
    @SneakyThrows
    @Override
    public Integer delete(Integer id) {
        try (var connection = ConnectionManager.get();
        var ps = connection.prepareStatement(DELETE_SQL)) {
            ps.setObject(1, id);
            return ps.executeUpdate();
        }
    }
    @SneakyThrows
    @Override
    public Trainer findById(Integer id) {
        try (var connection = ConnectionManager.get();
        var ps = connection.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setObject(1, id);
            var rs = ps.executeQuery();
            rs.next();
            return Trainer.builder()
                    .id(rs.getObject("id", Integer.class))
                    .name(rs.getString("name"))
                    .bio(rs.getString("bio"))
                    .contactPhone(rs.getString("contact_phone"))
                    .contactEmail(rs.getString("contact_email"))
                    .photo(rs.getString("photo"))
                    .build();
        }
    }
    @SneakyThrows
    @Override
    public Integer update(Trainer entity) {
        try (var connection = ConnectionManager.get();
        var ps = connection.prepareStatement(UPDATE_SQL);
        var ps2 = connection.prepareStatement(FIND_ALL_WITH_FILTER_SQL)) {
            ps2.setObject(1, entity.getId());
            System.out.println(ps2.executeQuery());
            ResultSet resultSet = ps2.executeQuery();
            while (resultSet.next()) {
                if (entity.getContactPhone().equals(resultSet.getString("contact_phone")) && entity.getContactEmail().equals(resultSet.getString("contact_email"))) {
                    throw new TrainerValidationException("Email and phone are exists");
                } else if (entity.getContactEmail().equals(resultSet.getString("contact_email"))) {
                    throw new EmailValidationException("This email is exists");
                } else if (entity.getContactPhone().equals(resultSet.getString("contact_phone"))) {
                    throw new EmailValidationException("This phone is exists");
                }
            }
            ps.setObject(1, entity.getName());
            ps.setObject(2, entity.getBio());
            ps.setObject(3, entity.getContactPhone());
            ps.setObject(4, entity.getContactEmail());
            ps.setObject(5, entity.getPhoto());
            ps.setObject(6, entity.getId());
            return ps.executeUpdate();
        }
    }

    @SneakyThrows
    public List<Trainer> findAll() {
        List<Trainer> trainers = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(FIND_ALL_TRAINERS_SQL)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Trainer trainer = new Trainer();
                trainer.setId(resultSet.getInt("id"));
                trainer.setName(resultSet.getString("name"));
                trainer.setBio(resultSet.getString("bio"));
                trainer.setContactPhone(resultSet.getString("contact_phone"));
                trainer.setContactEmail(resultSet.getString("contact_email"));
                trainer.setPhoto(resultSet.getString("photo"));
                trainers.add(trainer);
            }
            return trainers;
        }
    }
}
