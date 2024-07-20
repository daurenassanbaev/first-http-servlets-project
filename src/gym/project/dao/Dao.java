package gym.project.dao;


import gym.project.entity.ViewReview;

import java.util.List;

public interface Dao <E>{
    E save(E entity);
    Integer delete(Integer id);
    E findById(Integer id);
    Integer update(E id);
    List<E> findAll();
}
