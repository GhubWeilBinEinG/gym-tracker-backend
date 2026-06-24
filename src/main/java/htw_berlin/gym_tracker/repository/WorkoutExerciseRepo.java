package htw_berlin.gym_tracker.repository;

import htw_berlin.gym_tracker.entity.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExerciseRepo extends JpaRepository<WorkoutExercise, Long> {}