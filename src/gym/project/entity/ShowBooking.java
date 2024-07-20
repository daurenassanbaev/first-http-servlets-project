package gym.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowBooking {
    private Integer bookingId;
    private String username;
    private String email;
    private String trainerName;
    private String programName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
}
