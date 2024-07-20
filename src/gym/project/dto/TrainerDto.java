package gym.project.dto;
import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TrainerDto {
    Integer id;
    String name;
    String bio;
    String contactPhone;
    String contactEmail;
    Part photo;
}
