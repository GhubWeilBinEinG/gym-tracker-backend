package htw_berlin.gym_tracker.service;

import htw_berlin.gym_tracker.entity.Workout;
import htw_berlin.gym_tracker.repository.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepo workoutRepo;

    public Workout save(Workout workout) {
        return workoutRepo.save(workout);
    }

    public Workout get(Long id) {
        return workoutRepo.findById(id).orElseThrow(() -> new RuntimeException("Workout nicht gefunden!"));
    }
}