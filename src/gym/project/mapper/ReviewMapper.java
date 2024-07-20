package gym.project.mapper;


import gym.project.dto.ReviewDto;
import gym.project.dto.UserDto;
import gym.project.entity.Review;
import gym.project.entity.Schedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewMapper implements Mapper<ReviewDto, Review> {
    @Getter
    private static final ReviewMapper INSTANCE = new ReviewMapper();
    @Override
    public Review mapFrom(ReviewDto object) {
        return Review.builder()
                .userId(object.getUserId())
                .trainerId(object.getTrainerId())
                .rating(object.getRating())
                .comment(object.getComment())
                .build();
    }

    @Override
    public Review mapFromWithId(ReviewDto object) {
        return null;
    }
}
