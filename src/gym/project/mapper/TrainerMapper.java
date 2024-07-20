package gym.project.mapper;

import gym.project.dto.TrainerDto;
import gym.project.entity.Trainer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrainerMapper implements Mapper<TrainerDto, Trainer> {
    @Getter
    private static final TrainerMapper INSTANCE = new TrainerMapper();
    private static final String IMAGE_FOLDER = "trainers/";
    @Override
    public Trainer mapFrom(TrainerDto object) {
        return Trainer.builder()
                .name(object.getName())
                .bio(object.getBio())
                .contactPhone(object.getContactPhone())
                .contactEmail(object.getContactEmail())
                .photo(IMAGE_FOLDER + object.getPhoto().getSubmittedFileName())
                .build();
    }

    @Override
    public Trainer mapFromWithId(TrainerDto object) {
        return Trainer.builder()
                .id(object.getId())
                .name(object.getName())
                .bio(object.getBio())
                .contactPhone(object.getContactPhone())
                .contactEmail(object.getContactEmail())
                .photo(IMAGE_FOLDER + object.getPhoto().getSubmittedFileName())
                .build();
    }
}
