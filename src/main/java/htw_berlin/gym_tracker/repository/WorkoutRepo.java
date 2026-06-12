package htw_berlin.gym_tracker.repository;

import htw_berlin.gym_tracker.entity.Workout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepo extends CrudRepository<Workout, Long> { }