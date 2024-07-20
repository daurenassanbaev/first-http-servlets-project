package gym.project.dao;

import gym.project.entity.Review;
import gym.project.entity.ViewReview;
import gym.project.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDao implements Dao<Review> {
    @Getter
    private static final ReviewDao INSTANCE = new ReviewDao();
    private static final String SAVE_SQL = "INSERT INTO reviews (user_id, trainer_id, rating, comment) VALUES (?, ?, ?, ?)";
    private static final String FIND_ALL_SQL = "SELECT r.id as review_id, t.name as trainer_name, r.rating as rating, r.comment as comment FROM reviews as r INNER JOIN trainers as t ON r.trainer_id = t.id";
    private static final String FIND_ALL_ADMIN_SQL = "SELECT u.username, r.id as review_id, t.name as trainer_name, r.rating as rating, r.comment as comment FROM reviews as r INNER JOIN trainers as t ON r.trainer_id = t.id INNER JOIN users u ON u.id = r.user_id";
    private static final String DELETE_SQL = "DELETE FROM reviews WHERE id = ?";
    @SneakyThrows
    @Override
    public Review save(Review entity) {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, entity.getUserId());
            ps.setObject(2, entity.getTrainerId());
            ps.setObject(3, entity.getRating());
            ps.setObject(4, entity.getComment());
            ps.executeUpdate();
            var rs = ps.getGeneratedKeys();
            rs.next();
            entity.setId(rs.getInt(1));
            return entity;
        }
    }
    @SneakyThrows
    @Override
    public Integer delete(Integer id) {
        try (var connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(DELETE_SQL)) {
            ps.setObject(1, id);
            return ps.executeUpdate();
        }
    }

    @Override
    public Review findById(Integer id) {
        return null;
    }

    @Override
    public Integer update(Review id) {
        return 0;
    }

    @Override
    public List<Review> findAll() {
        return List.of();
    }

    @SneakyThrows
    public List<ViewReview> findAllViewReview() {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL)) {
            var rs = ps.executeQuery();
            List<ViewReview> reviews = new ArrayList<>();
            while (rs.next()) {
                reviews.add(ViewReview.builder()
                                .reviewId(rs.getObject("review_id", Integer.class))
                                .trainerName(rs.getObject("trainer_name", String.class))
                                .rating(rs.getObject("rating", Integer.class))
                                .comment(rs.getObject("comment", String.class))
                            .build());
            }
            return reviews;
        }
    }
    @SneakyThrows
    public List<ViewReview> findAllViewReviewAdmin() {
        try (Connection connection = ConnectionManager.get();
        PreparedStatement ps = connection.prepareStatement(FIND_ALL_ADMIN_SQL)) {
            var rs = ps.executeQuery();
            List<ViewReview> reviews = new ArrayList<>();
            while (rs.next()) {
                reviews.add(ViewReview.builder()
                                .username(rs.getString("username"))
                                .reviewId(rs.getObject("review_id", Integer.class))
                                .trainerName(rs.getObject("trainer_name", String.class))
                                .rating(rs.getObject("rating", Integer.class))
                                .comment(rs.getObject("comment", String.class))
                            .build());
            }
            return reviews;
        }
    }
}
