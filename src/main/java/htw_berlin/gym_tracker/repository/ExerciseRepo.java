package htw_berlin.gym_tracker.repository;

import htw_berlin.gym_tracker.entity.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepo extends CrudRepository<Exercise, Long> { }