package gym.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Review {
    private Integer id;
    private Integer userId;
    private Integer trainerId;
    private Integer rating;
    private String comment;
}
