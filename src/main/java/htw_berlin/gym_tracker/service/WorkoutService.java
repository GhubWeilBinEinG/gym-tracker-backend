package htw_berlin.gym_tracker.service;

import htw_berlin.gym_tracker.dto.AddExerciseDTO;
import htw_berlin.gym_tracker.entity.CustomExercise;
import htw_berlin.gym_tracker.entity.Workout;
import htw_berlin.gym_tracker.entity.WorkoutExercise;
import htw_berlin.gym_tracker.repository.WorkoutRepo;
import htw_berlin.gym_tracker.repository.WorkoutExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import htw_berlin.gym_tracker.repository.CustomExerciseRepo;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepo workoutRepo;

    @Autowired
    WorkoutExerciseRepo workoutExerciseRepo;

    @Autowired
    CustomExerciseRepo customExerciseRepo;

    public Workout save(Workout workout, String userId) {
        workout.setUserId(userId);
        return workoutRepo.save(workout);
    }

    public Workout get(Long id) {
        return workoutRepo.findById(id).orElseThrow(() -> new RuntimeException("Workout nicht gefunden!"));
    }

    public List<Workout> getAll(String userId) {
        return workoutRepo.findByUserId(userId);
    }

    public Workout addExercise(Long workoutId, AddExerciseDTO dto) {
        Workout workout = get(workoutId);

        WorkoutExercise we = new WorkoutExercise();
        if ("CUSTOM".equals(dto.source)) {
            CustomExercise ce = customExerciseRepo.findById(dto.customExerciseId)
                    .orElseThrow(() -> new RuntimeException("Custom Exercise nicht gefunden"));
            we.setExerciseName(ce.getName());
            we.setMuscle(ce.getMuscle());
        }else {
            we.setExerciseName(dto.exerciseName);
            we.setMuscle(dto.muscle);
        }

        we.setSets(dto.sets);
        we.setReps(dto.reps);
        we.setWorkout(workout);

        workout.getExercises().add(we);
        return workoutRepo.save(workout);
    }

    public void delete(Long workoutId, String userId) {
        Workout workout = workoutRepo.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout nicht gefunden"));
        if (!workout.getUserId().equals(userId)) {
            throw new RuntimeException("Keine Berechtigung");
        }
        workoutRepo.delete(workout);
    }


    public Workout removeExercise(Long workoutId, Long exerciseId, String userId) {
        Workout workout = workoutRepo.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout nicht gefunden"));
        if (!workout.getUserId().equals(userId)) {
            throw new RuntimeException("Keine Berechtigung");
        }

        WorkoutExercise toRemove = workoutExerciseRepo.findById(exerciseId)
                .orElseThrow(() -> new RuntimeException("Übung nicht gefunden"));

        workout.getExercises().removeIf(ex -> ex.getId().equals(exerciseId));
        workoutExerciseRepo.delete(toRemove);
        workoutRepo.save(workout);

        return workoutRepo.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout nicht gefunden"));
    }
}
