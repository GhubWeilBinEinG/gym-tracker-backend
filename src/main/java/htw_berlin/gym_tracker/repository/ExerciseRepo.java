package htw_berlin.gym_tracker.repository;

import htw_berlin.gym_tracker.entity.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Long> { }