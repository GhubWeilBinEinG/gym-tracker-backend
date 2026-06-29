package htw_berlin.gym_tracker.repository;

import htw_berlin.gym_tracker.entity.Workout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface WorkoutRepo extends JpaRepository<Workout, Long> {
    List<Workout> findByUserId(String userId);
}