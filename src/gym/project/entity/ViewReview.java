package gym.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ViewReview {
    private Integer reviewId;
    private String username;
    private String trainerName;
    private Integer rating;
    private String comment;
}