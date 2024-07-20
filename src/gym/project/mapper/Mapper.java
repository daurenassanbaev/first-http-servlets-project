package gym.project.mapper;

public interface Mapper <F, T> {
    T mapFrom(F object);
    T mapFromWithId(F object);
}
