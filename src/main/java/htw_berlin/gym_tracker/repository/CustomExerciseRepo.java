package htw_berlin.gym_tracker.repository;

import htw_berlin.gym_tracker.entity.CustomExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomExerciseRepo extends JpaRepository<CustomExercise, Long> {

    List<CustomExercise> findByUserId(String userId);

    Optional<CustomExercise> findByIdAndUserId(Long id, String userId);
}
