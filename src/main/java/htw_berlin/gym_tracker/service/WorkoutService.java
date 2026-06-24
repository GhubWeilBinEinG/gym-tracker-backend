package htw_berlin.gym_tracker.service;

import htw_berlin.gym_tracker.entity.Workout;
import htw_berlin.gym_tracker.entity.WorkoutExercise;
import htw_berlin.gym_tracker.repository.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Workout> getAll() {
        return workoutRepo.findAll();
    }

    public Workout addExercise(Long workoutId, WorkoutExercise exercise) {
        Workout workout = get(workoutId);
        exercise.setWorkout(workout);
        workout.getExercises().add(exercise);
        return workoutRepo.save(workout);
    }
}