package gym.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Trainer {
    private Integer id;
    private String name;
    private String bio;
    private String contactPhone;
    private String contactEmail;
    private String photo;
}
