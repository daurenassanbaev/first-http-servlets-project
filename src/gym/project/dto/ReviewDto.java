package gym.project.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ReviewDto {
    Integer id;
    Integer userId;
    Integer trainerId;
    Integer rating;
    String comment;
}
