package htw_berlin.gym_tracker.repository;

import htw_berlin.gym_tracker.entity.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkoutSessionRepo extends JpaRepository<WorkoutSession, Long> {

    List<WorkoutSession> findByUserSubjectOrderByDateDesc(String userSubject);
}