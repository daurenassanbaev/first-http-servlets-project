package gym.project.service;

import gym.project.dao.ReviewDao;
import gym.project.dto.ReviewDto;
import gym.project.dto.UserDto;
import gym.project.entity.Review;
import gym.project.entity.User;
import gym.project.entity.ViewReview;
import gym.project.mapper.ReviewMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewService {
    @Getter
    private static final ReviewService INSTANCE = new ReviewService();
    private final ReviewMapper reviewMapper = ReviewMapper.getINSTANCE();
    private final ReviewDao reviewDao = ReviewDao.getINSTANCE();
    @SneakyThrows
    public Integer create(ReviewDto reviewDto) {
        Review review = reviewMapper.mapFrom(reviewDto);
        Review review1 = reviewDao.save(review);
        return review1.getId();
    }
    public List<ViewReview> findAllViewReview() {
        return reviewDao.findAllViewReview();
    }
    public List<ViewReview> findAllViewReviewAdmin() {
        return reviewDao.findAllViewReviewAdmin();
    }

    public void delete(int reviewId) {
        reviewDao.delete(reviewId);
    }
}
